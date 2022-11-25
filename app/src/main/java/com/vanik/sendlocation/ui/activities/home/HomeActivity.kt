package com.vanik.sendlocation.ui.activities.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.vanik.sendlocation.R
import com.vanik.sendlocation.ui.BaseActivity
import com.vanik.sendlocation.ui.fragments.send.SendFragment


class HomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        showMessage("HomeActivity")
        val fragment: Fragment = SendFragment()

        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()

        transaction.replace(R.id.home_fragment, fragment).commit()
    }

    override fun setUpViews() {
        println()
    }
}