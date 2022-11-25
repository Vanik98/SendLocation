package com.vanik.sendlocation.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.vanik.sendlocation.domain.CheckIsUserSignInUseCase

class MainViewModel(
    private val checkIsUserSignInUseCase: CheckIsUserSignInUseCase
) : ViewModel() {
    fun isUserSignIn() = checkIsUserSignInUseCase.execute().asLiveData()


}