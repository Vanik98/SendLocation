package com.vanik.sendlocation.di

import com.google.firebase.auth.FirebaseAuth
import com.vanik.sendlocation.data.Repository
import com.vanik.sendlocation.domain.CheckIsUserSignInUseCase
import com.vanik.sendlocation.domain.LoginUseCase
import com.vanik.sendlocation.domain.RegistrationUseCase
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
//    viewModel{ LoginViewModel(get())}
}
private val useCaseModules = module {
    single { LoginUseCase(get()) }
    single { RegistrationUseCase(get()) }
    single { CheckIsUserSignInUseCase(get()) }
}

private val repositoryModule = module {
    single { Repository(get()) }
}

private val firebaseModule = module {
    single { FirebaseAuth.getInstance() }
}
