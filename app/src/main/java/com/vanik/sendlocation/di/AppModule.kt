package com.vanik.sendlocation.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthOptions
import com.vanik.sendlocation.data.Repository
import com.vanik.sendlocation.domain.*
import com.vanik.sendlocation.ui.login.LoginViewModel
import com.vanik.sendlocation.ui.main.MainViewModel
import com.vanik.sendlocation.ui.home.fragments.send.SendFragment
import com.vanik.sendlocation.ui.home.fragments.send.SendViewModel
import org.koin.androidx.viewmodel.dsl.viewModel


import org.koin.dsl.module

val appModules by lazy {
    listOf(
        viewModelsModules,
        useCaseModules,
        repositoryModule,
        firebaseModule
    )
}

private val viewModelsModules = module {
    viewModel { MainViewModel(get()) }
    viewModel { LoginViewModel(get(), get(), get()) }
    viewModel { SendViewModel(get()) }
}
private val useCaseModules = module {
    single { SendSmsUseCase(get()) }
    single { LoginUseCase(get()) }
    single { VerifyUseCase(get()) }
    single { CheckIsUserSignInUseCase(get()) }
    single { GetContactsUseCase(get()) }
}

private val repositoryModule = module {
    single { Repository(get(), get(),get()) }
}

private val firebaseModule = module {
    single { FirebaseAuth.getInstance() }
    single { PhoneAuthOptions.newBuilder(get()) }
}
