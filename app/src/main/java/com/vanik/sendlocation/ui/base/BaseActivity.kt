package com.vanik.sendlocation.ui.base

import android.R
import android.app.Dialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    private lateinit var dialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpBaseViews()
        setUpViews()
    }

    abstract fun setUpViews()

    fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun setUpBaseViews() {
        initializeDialog()
    }

    private fun initializeDialog() {
        dialog = Dialog(this, R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen)
        dialog.setContentView(com.vanik.sendlocation.R.layout.dialog_layout)
    }

    fun showDialog() {
        dialog.show()
    }

    fun closeDialog() {
        dialog.dismiss()
    }

    fun logChat(message: String){
        Log.i("vanikTest",message)
    }

    fun isInternetAvailable(): Boolean {
        var isConnected: Boolean = false // Initial Value
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected
    }
}