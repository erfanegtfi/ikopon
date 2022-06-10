package com.ikopon.ikopon.data.repository

import com.ikopon.ikopon.data.dataSource.local.LocalDataSource
import com.ikopon.ikopon.data.dataSource.remote.RemoteDataSource
import com.ikopon.ikopon.data.repository.base.AnyParam
import com.ikopon.ikopon.data.repository.base.ApiCallState
import com.ikopon.ikopon.data.repository.base.StrategyFlowRepository
import com.ikopon.ikopon.model.City
import com.ikopon.ikopon.model.base.ApiListResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.Response

@ExperimentalCoroutinesApi
class CityRepository constructor(
    private val cityLocalDataSource: LocalDataSource<List<City>>,
    private val cityRemoteDataSource: RemoteDataSource<ApiListResponse<City>>,
) : StrategyFlowRepository<ApiListResponse<City>, AnyParam>() {

    override fun getLocal(param: AnyParam): Flow<ApiCallState<ApiListResponse<City>>> {
        return cityLocalDataSource.readFromLocal().map {
            ApiCallState.Success(data = ApiListResponse(data = it))
        }
    }

    override suspend fun getRemote(param: AnyParam): Response<ApiListResponse<City>> {
        return cityRemoteDataSource.readFromApi()
    }

    override suspend fun saveRemote(response: ApiListResponse<City>?) {
        if (response?.data != null)
            cityLocalDataSource.writeToLocal(response.data)
    }

}