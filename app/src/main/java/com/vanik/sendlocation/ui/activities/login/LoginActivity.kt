package com.vanik.sendlocation.ui.activities.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.vanik.sendlocation.R
import com.vanik.sendlocation.databinding.ActivityLoginBinding
import com.vanik.sendlocation.ui.BaseActivity
import com.vanik.sendlocation.ui.activities.home.HomeActivity
import java.util.concurrent.TimeUnit

class LoginActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var num0: TextView
    private lateinit var num1: TextView
    private lateinit var num2: TextView
    private lateinit var num3: TextView
    private lateinit var num4: TextView
    private lateinit var num5: TextView
    private lateinit var num6: TextView
    private lateinit var num7: TextView
    private lateinit var num8: TextView
    private lateinit var num9: TextView
    private lateinit var numDelete: TextView
    private lateinit var phoneNumberTv: TextView
    private lateinit var confirmTv: TextView
    private lateinit var verifyNum1: TextView
    private lateinit var verifyNum2: TextView
    private lateinit var verifyNum3: TextView
    private lateinit var verifyNum4: TextView
    private lateinit var verifyNum5: TextView
    private lateinit var verifyNum6: TextView
    private lateinit var backIv: ImageView
    private lateinit var resentTv: TextView
    private lateinit var verifyTv : TextView
    private lateinit var verifySpaceTv : TextView
    private var number = "+374 "
    private var verifyNumber = ""
    private var showVerificationView = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        confirm()
        verify()
        back()
    }

    override fun setUpViews() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val view = binding.root
        num0 = view.findViewById(R.id.tv_num_0)
        num1 = view.findViewById(R.id.tv_num_1)
        num2 = view.findViewById(R.id.tv_num_2)
        num3 = view.findViewById(R.id.tv_num_3)
        num4 = view.findViewById(R.id.tv_num_4)
        num5 = view.findViewById(R.id.tv_num_5)
        num6 = view.findViewById(R.id.tv_num_6)
        num7 = view.findViewById(R.id.tv_num_7)
        num8 = view.findViewById(R.id.tv_num_8)
        num9 = view.findViewById(R.id.tv_num_9)
        numDelete = view.findViewById(R.id.tv_num_delete)
        verifyNum1 = view.findViewById(R.id.tv_verify_number1)
        verifyNum2 = view.findViewById(R.id.tv_verify_number2)
        verifyNum3 = view.findViewById(R.id.tv_verify_number3)
        verifyNum4 = view.findViewById(R.id.tv_verify_number4)
        verifyNum5 = view.findViewById(R.id.tv_verify_number5)
        verifyNum6 = view.findViewById(R.id.tv_verify_number6)
        phoneNumberTv = view.findViewById(R.id.tv_phone_number)
        confirmTv = view.findViewById(R.id.tv_confirm)
        backIv = view.findViewById(R.id.iv_back)
        resentTv = view.findViewById(R.id.tv_resend_code)
        verifyTv = view.findViewById(R.id.tv_verify)
        verifySpaceTv = view.findViewById(R.id.tv_verify_number_space)
        numClicks()
    }

    private fun back(){
        backIv.setOnClickListener {
            showVerificationView = false
            changeView()
            confirmTv.visibility = View.VISIBLE
            verifyTv.visibility = View.GONE
        }
    }

    private fun confirm(){
        confirmTv.setOnClickListener {
            showVerificationView = true
            changeView()
            confirmTv.visibility = View.GONE
            verifyTv.visibility = View.VISIBLE
            //            sendSms()
        }
    }

    private fun verify(){
        verifyTv.setOnClickListener{
            changeView()
        }
    }
    private fun changeView() {
        if (!showVerificationView) {
            backIv.visibility = View.GONE
            verifySpaceTv.visibility = View.GONE
            verifyNum1.visibility = View.GONE
            verifyNum2.visibility = View.GONE
            verifyNum3.visibility = View.GONE
            verifyNum4.visibility = View.GONE
            verifyNum5.visibility = View.GONE
            verifyNum6.visibility = View.GONE
            resentTv.visibility = View.GONE
            phoneNumberTv.visibility = View.VISIBLE
            verifyNumber = ""
            verifyNum1.text = ""
            verifyNum2.text = ""
            verifyNum3.text = ""
            verifyNum4.text = ""
            verifyNum5.text = ""
            verifyNum6.text = ""
        } else {
            backIv.visibility = View.VISIBLE
            verifySpaceTv.visibility = View.VISIBLE
            verifyNum1.visibility = View.VISIBLE
            verifyNum2.visibility = View.VISIBLE
            verifyNum3.visibility = View.VISIBLE
            verifyNum4.visibility = View.VISIBLE
            verifyNum5.visibility = View.VISIBLE
            verifyNum6.visibility = View.VISIBLE
            resentTv.visibility = View.VISIBLE
            phoneNumberTv.visibility = View.GONE
        }
    }

    private fun numClicks() {
        num0.setOnClickListener {
            Log.i("vanikTest", "0 = $showVerificationView")
            when (showVerificationView) {
                false -> setPhoneNumberTextView(0)
                true -> setVerificationTextViews(0)
            }
        }
        num1.setOnClickListener() {
            when (showVerificationView) {
                false -> setPhoneNumberTextView(1)
                true -> setVerificationTextViews(1)
            }
        }
        num2.setOnClickListener() {
            when (showVerificationView) {
                false -> setPhoneNumberTextView(2)
                true -> setVerificationTextViews(2)
            }
        }
        num3.setOnClickListener() {
            when (showVerificationView) {
                false -> setPhoneNumberTextView(3)
                true -> setVerificationTextViews(3)
            }
        }
        num4.setOnClickListener() {
            when (showVerificationView) {
                false -> setPhoneNumberTextView(4)
                true -> setVerificationTextViews(4)
            }
        }
        num5.setOnClickListener() {
            when (showVerificationView) {
                false -> setPhoneNumberTextView(5)
                true -> setVerificationTextViews(5)
            }
        }
        num6.setOnClickListener() {
            when (showVerificationView) {
                false -> setPhoneNumberTextView(6)
                true -> setVerificationTextViews(6)
            }
        }
        num7.setOnClickListener() {
            when (showVerificationView) {
                false -> setPhoneNumberTextView(7)
                true -> setVerificationTextViews(7)
            }
        }
        num8.setOnClickListener() {
            when (showVerificationView) {
                false -> setPhoneNumberTextView(8)
                true -> setVerificationTextViews(8)
            }
        }
        num9.setOnClickListener() {
            when (showVerificationView) {
                false -> setPhoneNumberTextView(9)
                true -> setVerificationTextViews(9)
            }
        }
        numDelete.setOnClickListener {
            when (showVerificationView) {
                false -> {
                    if (number.length > 5) {
                        number = number.trim().dropLast(1)
                    }
                    phoneNumberTv.text = number
                }
                true -> {
                    verifyNumber = verifyNumber.trim().dropLast(1)
                    when (verifyNumber.length) {
                        0 -> verifyNum1.text = ""
                        1 -> verifyNum2.text = ""
                        2 -> verifyNum3.text = ""
                        3 -> verifyNum4.text = ""
                        4 -> verifyNum5.text = ""
                        5 -> verifyNum6.text = ""
                    }
                }
            }

        }
    }

    private fun setPhoneNumberTextView(n: Int) {
        if(number.trim().length <= 12) {
            number += n
            if (number.length % 3 != 0) {
                number += " "
            }
            phoneNumberTv.text = number
        }else{
                showMessage("The number of digits exceeds the phone number length limit")
        }
    }

    private fun setVerificationTextViews(n: Int) {
        if (verifyNumber.length < 6) {
            when (verifyNumber.length) {
                0 -> verifyNum1.text = n.toString()
                1 -> verifyNum2.text = n.toString()
                2 -> verifyNum3.text = n.toString()
                3 -> verifyNum4.text = n.toString()
                4 -> verifyNum5.text = n.toString()
                5 -> verifyNum6.text = n.toString()
            }
            verifyNumber += n
        }
    }

    private fun sendSms() {
        val auth = FirebaseAuth.getInstance()
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(number.trim())
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(p: PhoneAuthCredential) {
                    Log.i("vanikTest", "onVerificationCompleted")
                }

                override fun onVerificationFailed(p0: FirebaseException) {
                    Log.i("vanikTest", "onVerificationFailed")
                }

            })
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun login(credential: PhoneAuthCredential) {
        val auth = FirebaseAuth.getInstance()
        auth.signInWithCredential(credential).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                startActivity(Intent(this, HomeActivity::class.java))
                this.finish()
            } else {

            }
        }
    }
}