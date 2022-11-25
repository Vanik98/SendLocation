package com.vanik.sendlocation.ui.login

import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*
import com.vanik.sendlocation.domain.LoginUseCase
import com.vanik.sendlocation.domain.SendSmsUseCase
import com.vanik.sendlocation.domain.VerifyUseCase

class LoginViewModel(
    private val sendSmsUseCase: SendSmsUseCase,
    private val loginUseCase: LoginUseCase,
    private val verifyUseCase: VerifyUseCase
) : ViewModel() {

    fun sendSms() = sendSmsUseCase.execute()

    fun login(firebaseVerifyCode: String, verifyNumber: String) = loginUseCase.execute(firebaseVerifyCode, verifyNumber)

    fun verify(options : PhoneAuthOptions) = verifyUseCase.execute(options)

}