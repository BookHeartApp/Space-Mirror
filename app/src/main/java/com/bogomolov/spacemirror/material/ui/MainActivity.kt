package com.bogomolov.spacemirror.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bogomolov.spacemirror.R
import com.bogomolov.spacemirror.material.ui.picture.PictureOfTheDayFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PictureOfTheDayFragment.newInstance())
                .commitNow()
        }
    }
}