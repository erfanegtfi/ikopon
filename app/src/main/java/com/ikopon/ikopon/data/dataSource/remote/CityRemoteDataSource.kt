package com.ikopon.ikopon.data.dataSource.remote

import com.ikopon.ikopon.core.baseResponse.ApiListResponse
import com.ikopon.ikopon.data.dataSource.remote.api.CityApi
import com.ikopon.ikopon.data.model.CityDataModel
import com.ikopon.ikopon.domain.entities.City
import retrofit2.Response

interface CityRemoteDataSource {
    suspend fun getCityList(): Response<ApiListResponse<CityDataModel>>
}

class CityRemoteDataSourceImpl constructor(
    private val cityApi: CityApi
) : CityRemoteDataSource {

    override suspend fun getCityList(): Response<ApiListResponse<CityDataModel>> {
        return cityApi.getCityList()
    }

}