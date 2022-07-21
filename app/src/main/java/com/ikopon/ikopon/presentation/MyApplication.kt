package com.ikopon.ikopon.presentation

import android.app.Application
import com.ikopon.ikopon.data.di.appNetworkModule
import com.ikopon.ikopon.data.di.appStorageModule
import com.ikopon.ikopon.data.di.networkDependency
import com.ikopon.ikopon.data.di.repositoryDependency
import com.ikopon.ikopon.presentation.ui.profile.city.di.cityModule
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
            modules(appNetworkModule)
            modules(appStorageModule)

            modules(networkDependency)
            modules(repositoryDependency)


            modules(cityModule)
        }
    }
}