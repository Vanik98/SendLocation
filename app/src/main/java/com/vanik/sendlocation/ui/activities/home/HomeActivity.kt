package com.vanik.sendlocation.ui.activities.home

import android.os.Bundle
import com.vanik.sendlocation.R
import com.vanik.sendlocation.ui.BaseActivity

class HomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        showMessage("HomeActivity")
    }

    override fun setUpViews() {
        println()
    }
}