package com.vanik.sendlocation.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthOptions
import com.vanik.sendlocation.data.Repository
import com.vanik.sendlocation.domain.CheckIsUserSignInUseCase
import com.vanik.sendlocation.domain.LoginUseCase
import com.vanik.sendlocation.domain.SendSmsUseCase
import com.vanik.sendlocation.domain.VerifyUseCase
import com.vanik.sendlocation.ui.activities.login.LoginViewModel
import com.vanik.sendlocation.ui.activities.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel


import org.koin.dsl.module
val appModules by lazy {
    listOf(
        viewModelsModule,
        useCaseModules,
        repositoryModule,
        firebaseModule
    )
}

private val viewModelsModule = module {
    viewModel { MainViewModel(get()) }
    viewModel{ LoginViewModel(get(),get(),get()) }
}
private val useCaseModules = module {
    single { SendSmsUseCase(get()) }
    single { LoginUseCase(get()) }
    single { VerifyUseCase(get()) }
    single { CheckIsUserSignInUseCase(get()) }
}

private val repositoryModule = module {
    single { Repository(get(),get()) }
}

private val firebaseModule = module {
    single { FirebaseAuth.getInstance() }
    single { PhoneAuthOptions.newBuilder(get()) }
}
