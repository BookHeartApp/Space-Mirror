package com.bogomolov.spacemirror.presentation.view.cases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import com.bogomolov.spacemirror.data.local.cases.SimpleCase
import com.bogomolov.spacemirror.data.local.repository.CasesRepository
import com.bogomolov.spacemirror.data.local.repository.CasesRepositoryImpl
import com.bogomolov.spacemirror.databinding.FragmentListOfCasesBinding
import com.bogomolov.spacemirror.utils.recyclerview.ItemTouchHelperCallback

class ListOfCasesFragment(
    private val casesRepository: CasesRepository = CasesRepositoryImpl()
) : Fragment() {

    private val adapter: ListOfCasesAdapter by lazy { ListOfCasesAdapter() }

    private var _binding: FragmentListOfCasesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListOfCasesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.cases = casesRepository.getCases()

        with(binding) {
            listOfCasesRv.adapter = adapter
            listOfCasesRv.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    LinearLayout.VERTICAL
                )
            )

            addCase.setOnClickListener {
                adapter.addCase(SimpleCase("новое дело"))
            }

            ItemTouchHelper(ItemTouchHelperCallback(adapter)).attachToRecyclerView(listOfCasesRv)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}