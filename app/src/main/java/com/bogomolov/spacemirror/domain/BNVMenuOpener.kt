package com.bogomolov.spacemirror.domain

import androidx.fragment.app.FragmentManager
import com.bogomolov.spacemirror.R
import com.bogomolov.spacemirror.presentation.view.api.viewpager.ViewPagerFragment
import com.bogomolov.spacemirror.presentation.view.cases.ListOfCasesFragment
import com.bogomolov.spacemirror.presentation.view.home.HomeFragment
import com.bogomolov.spacemirror.presentation.view.settings.SettingsFragment

class BNVMenuOpener(private val fragmentManager: FragmentManager) {

    fun openHomeFragment() {
        fragmentManager.beginTransaction()
            .replace(R.id.main_activity_container, HomeFragment())
            .commitAllowingStateLoss()
    }

    fun openViewPagerFragment() {
        fragmentManager.beginTransaction()
            .replace(R.id.main_activity_container, ViewPagerFragment())
            .commitAllowingStateLoss()
    }

    fun openListOfCasesFragment() {
        fragmentManager.beginTransaction()
            .replace(R.id.main_activity_container, ListOfCasesFragment())
            .commitAllowingStateLoss()
    }

    fun openSettingsFragment() {
        fragmentManager.beginTransaction()
            .replace(R.id.main_activity_container, SettingsFragment())
            .commitAllowingStateLoss()
    }
}