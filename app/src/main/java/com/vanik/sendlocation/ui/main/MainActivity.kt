package com.vanik.sendlocation.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.vanik.sendlocation.R
import com.vanik.sendlocation.ui.base.BaseActivity
import com.vanik.sendlocation.ui.home.HomeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkIsUserSignIn()
        showMessage("MainActivity")
    }

    override fun setUpViews() {
        Log.i("","")
    }

    private fun checkIsUserSignIn() {
//        viewModel.isUserSignIn().observe(this) {
//            if (it == true) {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
//            } else {
//                startActivity(Intent(this, LoginActivity::class.java))
//                finish()
//            }
      //  }
    }
}