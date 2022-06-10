package com.ikopon.ikopon.presentation.ui.profile.city.di

import com.ikopon.ikopon.data.dataSource.local.CityLocalDataSource
import com.ikopon.ikopon.data.dataSource.local.LocalDataSource
import com.ikopon.ikopon.data.dataSource.local.room.IkoponDatabase
import com.ikopon.ikopon.data.dataSource.remote.CityRemoteDataSource
import com.ikopon.ikopon.data.dataSource.remote.RemoteDataSource
import com.ikopon.ikopon.data.dataSource.remote.api.CityApi
import com.ikopon.ikopon.data.repository.CityRepository
import com.ikopon.ikopon.model.City
import com.ikopon.ikopon.model.base.ApiListResponse
import com.ikopon.ikopon.presentation.ui.profile.city.CityViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

@ExperimentalCoroutinesApi
val networkDependency = module {

    factory { get<Retrofit>().create(CityApi::class.java)}
    factory<LocalDataSource<List<City>>> {
        CityLocalDataSource(get())
    }
    factory<RemoteDataSource<ApiListResponse<City>>> {
        CityRemoteDataSource(get())
    }

    factory { CityRepository(get(), get()) }
}

@ExperimentalCoroutinesApi
val cityModule = module {
    viewModel { CityViewModel(get()) }

    factory {
        get<IkoponDatabase>().getCityDao()
    }
}

