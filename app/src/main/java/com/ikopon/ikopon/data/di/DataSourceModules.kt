package com.ikopon.ikopon.data.di

import com.ikopon.ikopon.data.dataSource.local.CityLocalDataSource
import com.ikopon.ikopon.data.dataSource.local.LocalDataSource
import com.ikopon.ikopon.data.dataSource.remote.CityRemoteDataSource
import com.ikopon.ikopon.data.dataSource.remote.CityRemoteDataSourceImpl
import com.ikopon.ikopon.data.dataSource.remote.api.CityApi
import com.ikopon.ikopon.data.model.CityDataModel
import com.ikopon.ikopon.data.repository.CityRepositoryImp
import com.ikopon.ikopon.domain.repositories.CityRepository
import org.koin.dsl.module
import retrofit2.Retrofit

private const val BASE_URL = "https://apid.ikopon.ir/"
private const val REQUEST_TIMEOUT_DURATION = 30L

val networkDependency = module {

    factory { get<Retrofit>().create(CityApi::class.java) }


    factory<CityRemoteDataSource> {
        CityRemoteDataSourceImpl(get())
    }
    //

    factory<LocalDataSource<List<CityDataModel>>> {
        CityLocalDataSource(get())
    }

}

val repositoryDependency = module {
    factory<CityRepository> { CityRepositoryImp(get(), get()) }
}





