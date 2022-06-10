package com.ikopon.ikopon.di

import com.google.gson.GsonBuilder
import com.ikopon.ikopon.data.dataSource.local.CityLocalDataSource
import com.ikopon.ikopon.data.dataSource.local.preferences.Session
import com.ikopon.ikopon.data.dataSource.local.preferences.SessionManager
import com.ikopon.ikopon.data.dataSource.local.preferences.SessionManagerImp
import com.ikopon.ikopon.data.dataSource.local.room.IkoponDatabase
import com.ikopon.ikopon.data.dataSource.remote.CityRemoteDataSource
import com.ikopon.ikopon.data.dataSource.remote.api.CityApi
import com.ikopon.ikopon.data.repository.CityRepository
import com.ikopon.ikopon.data.utils.NetInterceptor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://apid.ikopon.ir/"
private const val REQUEST_TIMEOUT_DURATION = 30L

val repositoryModule = module {
    single<SessionManager> {
        SessionManagerImp(get())
    }

    single {
        Session(get())
    }

    single {
        IkoponDatabase.getInstance(get())
    }

    factory {
        get<IkoponDatabase>().getCityDao()
    }
}

val appModule = module {
    single {
        GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().create()
    }
    single {
        OkHttpClient.Builder()
            .addInterceptor(NetInterceptor())
            .connectTimeout(REQUEST_TIMEOUT_DURATION, TimeUnit.SECONDS)
            .readTimeout(REQUEST_TIMEOUT_DURATION, TimeUnit.SECONDS)
            .writeTimeout(REQUEST_TIMEOUT_DURATION, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder().client(get())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(get()))
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().serializeNulls().create()))
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }

}