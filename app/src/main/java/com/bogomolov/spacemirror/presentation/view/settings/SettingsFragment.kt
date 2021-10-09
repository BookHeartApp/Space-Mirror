package com.bogomolov.spacemirror.presentation.view.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bogomolov.spacemirror.R
import com.bogomolov.spacemirror.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private var showThemes = false

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            settingsThemeBlue.setOnClickListener {
                requireActivity().setTheme(R.style.MyAppTheme_BlueTheme)
                requireActivity().recreate()
            }
            settingsThemeYellow.setOnClickListener {
                requireActivity().setTheme(R.style.MyAppTheme_YellowTheme)
                requireActivity().recreate()
            }
            settingsThemeGreen.setOnClickListener {
                requireActivity().setTheme(R.style.MyAppTheme_GreenTheme)
                requireActivity().recreate()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}