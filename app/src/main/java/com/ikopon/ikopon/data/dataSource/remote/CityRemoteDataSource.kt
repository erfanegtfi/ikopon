package com.ikopon.ikopon.data.dataSource.remote

import com.ikopon.ikopon.data.dataSource.remote.api.CityApi
import com.ikopon.ikopon.model.City
import com.ikopon.ikopon.model.base.ApiListResponse
import retrofit2.Response

class CityRemoteDataSource constructor(
    private val cityApi: CityApi
) : RemoteDataSource<ApiListResponse<City>> {

    override suspend fun readFromApi(): Response<ApiListResponse<City>> {
        return cityApi.getCityList()
    }

}