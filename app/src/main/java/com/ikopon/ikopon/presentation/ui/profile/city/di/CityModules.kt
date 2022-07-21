package com.ikopon.ikopon.presentation.ui.profile.city.di

import com.ikopon.ikopon.data.dataSource.local.room.IkoponDatabase
import com.ikopon.ikopon.presentation.ui.profile.city.CityViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


@ExperimentalCoroutinesApi
val cityModule = module {
    viewModel { CityViewModel(get()) }

    factory {
        get<IkoponDatabase>().getCityDao()
    }
}

