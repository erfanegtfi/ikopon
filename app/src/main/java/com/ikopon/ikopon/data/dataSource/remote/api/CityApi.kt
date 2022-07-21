package com.ikopon.ikopon.data.dataSource.remote.api

import com.ikopon.ikopon.core.baseResponse.ApiListResponse
import com.ikopon.ikopon.data.model.CityDataModel
import com.ikopon.ikopon.domain.entities.City
import retrofit2.Response
import retrofit2.http.GET

interface CityApi {

    @GET("public/city/list/")
    suspend fun getCityList(): Response<ApiListResponse<CityDataModel>>
}