package com.bogomolov.spacemirror.presentation.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bogomolov.spacemirror.R
import com.bogomolov.spacemirror.databinding.ActivityMainBinding
import com.bogomolov.spacemirror.domain.BNVMenuOpener
import com.bogomolov.spacemirror.presentation.view.home.HomeFragment

class MainActivity : AppCompatActivity() {

    private val bnvMenuOpener: BNVMenuOpener = BNVMenuOpener(supportFragmentManager)

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.mainActivityContainer.id, HomeFragment())
                .commitNow()
        }

        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bnv_home -> {
                    bnvMenuOpener.openHomeFragment()
                    true
                }
                R.id.bnv_api -> {
                    bnvMenuOpener.openViewPagerFragment()
                    true
                }
                R.id.bnv_list_of_cases -> {
                    bnvMenuOpener.openListOfCasesFragment()
                    true
                }
                R.id.bnv_settings -> {
                    bnvMenuOpener.openSettingsFragment()
                    true
                }
                else -> {
                    bnvMenuOpener.openHomeFragment()
                    true
                }
            }
        }

        binding.bottomNavigationView.setOnNavigationItemReselectedListener { item ->
            when (item.itemId) {
                R.id.bnv_home -> {
                    bnvMenuOpener.openHomeFragment()
                }
                R.id.bnv_api -> {
                    bnvMenuOpener.openViewPagerFragment()
                }
                R.id.bnv_list_of_cases -> {
                    bnvMenuOpener.openListOfCasesFragment()
                }
                R.id.bnv_settings -> {
                    bnvMenuOpener.openSettingsFragment()
                }
            }
        }
    }
}