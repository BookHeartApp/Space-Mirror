package com.bogomolov.spacemirror.presentation.view.api.mars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.transition.ChangeBounds
import androidx.transition.ChangeImageTransform
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import coil.api.load
import com.bogomolov.spacemirror.R
import com.bogomolov.spacemirror.databinding.FragmentMarsBinding
import com.bogomolov.spacemirror.presentation.viewmodel.mars.MRFState
import com.bogomolov.spacemirror.presentation.viewmodel.mars.MRFViewModel
import com.bogomolov.spacemirror.utils.hide
import com.bogomolov.spacemirror.utils.show
import com.bogomolov.spacemirror.utils.toast

class MarsFragment : Fragment() {

    private var isExpanded = false

    private val viewModel: MRFViewModel by lazy {
        ViewModelProvider(this).get(MRFViewModel::class.java)
    }

    private var _binding: FragmentMarsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData().observe(viewLifecycleOwner) { renderData(it) }

        with(binding) {
            marsImageView.setOnClickListener {
                isExpanded = !isExpanded
                TransitionManager.beginDelayedTransition(
                    marsFragment, TransitionSet()
                        .addTransition(ChangeBounds())
                        .addTransition(ChangeImageTransform())
                )

                val params: ViewGroup.LayoutParams = marsImageView.layoutParams
                params.height =
                    if (isExpanded) ViewGroup.LayoutParams.MATCH_PARENT else ViewGroup.LayoutParams.MATCH_PARENT
                marsImageView.layoutParams = params
                marsImageView.scaleType =
                    if (isExpanded) ImageView.ScaleType.CENTER_CROP else ImageView.ScaleType.FIT_CENTER
            }
        }
    }

    private fun renderData(state: MRFState) {
        when (state) {
            is MRFState.Success -> {
                binding.marsFragment.show()
                binding.includedLoadingLayout.loadingLayout.hide()
                val serverResponseData = state.serverResponseData
                val listPhotos = serverResponseData.photos
                val photoUrl = listPhotos[50].imgSrc
                if (listPhotos.isNullOrEmpty()) {
                    toast("ListPhotos Link is Empty")
                } else {
                    binding.marsImageView.load(photoUrl) {
                        lifecycle(this@MarsFragment)
                        error(R.drawable.ic_load_error_vector)
                        placeholder(R.drawable.ic_no_photo_vector)
                    }
                }
            }
            is MRFState.Loading -> {
                binding.marsFragment.hide()
                binding.includedLoadingLayout.loadingLayout.show()
            }
            is MRFState.Error -> {
                binding.marsFragment.show()
                binding.includedLoadingLayout.loadingLayout.hide()
                toast("Error")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}