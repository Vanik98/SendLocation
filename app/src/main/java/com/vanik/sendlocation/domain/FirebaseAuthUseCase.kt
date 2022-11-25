package com.vanik.sendlocation.domain

import androidx.lifecycle.asLiveData
import com.google.firebase.auth.PhoneAuthOptions
import com.vanik.sendlocation.data.Repository

class LoginUseCase(private val repository: Repository) {
    fun execute(firebaseVerifyId : String,verifyNumber : String) = repository.login(firebaseVerifyId,verifyNumber)
}
class SendSmsUseCase(private val repository: Repository){
    fun execute() = repository.sendSms()
}

class VerifyUseCase(private val repository: Repository){
    fun execute(options: PhoneAuthOptions) = repository.verify(options)
}
class CheckIsUserSignInUseCase(private val repository: Repository){
    fun execute()  = repository.isUserSignIn()
}
