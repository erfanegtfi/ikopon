package com.ikopon.ikopon.presentation

import android.app.Application
import com.ikopon.ikopon.di.appModule
import com.ikopon.ikopon.di.repositoryModule
import com.ikopon.ikopon.presentation.ui.profile.city.di.cityModule
import com.ikopon.ikopon.presentation.ui.profile.city.di.networkDependency
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

@ExperimentalCoroutinesApi
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // start Koin!
        startKoin {
            // declare used Android context
            androidContext(this@MyApplication)
            // declare modules
            modules(appModule)
            modules(repositoryModule)
            modules(networkDependency)
            modules(cityModule)
        }
    }
}