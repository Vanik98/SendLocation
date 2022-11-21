package com.vanik.sendlocation.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.vanik.sendlocation.R
import com.vanik.sendlocation.ui.base.BaseActivity
import com.vanik.sendlocation.ui.home.HomeActivity
import com.vanik.sendlocation.ui.launcher.LauncherActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkIsUserSignIn()
    }

    override fun setUpViews() {
        Log.i("","")
    }

    private fun checkIsUserSignIn() {
//        if (viewModel.isUserSignIn()) {
//            startActivity(Intent(this, HomeActivity::class.java))
//            finish()
//        } else {
//            startActivity(Intent(this, LauncherActivity::class.java))
//            finish()
//        }
    }
}