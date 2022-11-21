package com.vanik.sendlocation

import android.app.Application
import com.vanik.sendlocation.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class SendLocationApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() = org.koin.core.context.startKoin {
        androidLogger(Level.DEBUG)
        androidContext(this@SendLocationApp)
        modules(appModules)
    }
}