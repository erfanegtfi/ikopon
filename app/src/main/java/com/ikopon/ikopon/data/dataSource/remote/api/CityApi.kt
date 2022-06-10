package com.ikopon.ikopon.data.dataSource.remote.api

import com.ikopon.ikopon.model.City
import com.ikopon.ikopon.model.base.ApiListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CityApi {

    @GET("public/city/list/")
    suspend fun getCityList(): Response<ApiListResponse<City>>
}