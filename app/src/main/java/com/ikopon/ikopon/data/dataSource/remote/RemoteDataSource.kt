package com.ikopon.ikopon.data.dataSource.remote

import retrofit2.Response


interface RemoteDataSource<T> {

    suspend fun readFromApi(): Response<T>

}