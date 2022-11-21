package com.vanik.sendlocation.domain

import com.vanik.sendlocation.data.Repository

class LoginUseCase(private val repository: Repository){
    fun execute(){

    }
}
class RegistrationUseCase(private val repository: Repository){
    fun execute(){

    }
}
class CheckIsUserSignInUseCase(private val repository: Repository){
    fun execute() : Boolean{
        return false
    }
}