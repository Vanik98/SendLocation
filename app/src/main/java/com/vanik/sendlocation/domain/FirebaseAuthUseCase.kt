package com.vanik.sendlocation.domain

import androidx.lifecycle.asLiveData
import com.vanik.sendlocation.data.Repository

class LoginUseCase(private val repository: Repository){
    fun execute(){

    }
}
class RegistrationUseCase(private val repository: Repository){
    fun execute(phoneNumber : String) = repository.registration(phoneNumber)
}
class CheckIsUserSignInUseCase(private val repository: Repository){
    fun execute()  = repository.isUserSignIn().asLiveData()
}