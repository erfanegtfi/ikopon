package com.ikopon.ikopon.data.repository

import com.ikopon.ikopon.core.abstraction.entity.AnyParam
import com.ikopon.ikopon.core.baseResponse.ApiListResponse
import com.ikopon.ikopon.core.utils.ApiCallState
import com.ikopon.ikopon.data.dataSource.local.LocalDataSource
import com.ikopon.ikopon.data.dataSource.remote.CityRemoteDataSource
import com.ikopon.ikopon.data.model.CityDataModel
import com.ikopon.ikopon.data.model.mapper.CityDataModelMapper
import com.ikopon.ikopon.data.repository.base.RepositoryStrategy
import com.ikopon.ikopon.data.repository.base.StrategyFlowRepository
import com.ikopon.ikopon.domain.entities.City
import com.ikopon.ikopon.domain.repositories.CityRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.Response

@ExperimentalCoroutinesApi
class CityRepositoryImp constructor(
    private val cityLocalDataSource: LocalDataSource<List<CityDataModel>>,
    private val cityRemoteDataSource: CityRemoteDataSource,
) : StrategyFlowRepository<ApiListResponse<CityDataModel>, AnyParam>(), CityRepository {

    override fun getLocal(param: AnyParam): Flow<ApiCallState<ApiListResponse<CityDataModel>>> {
        return cityLocalDataSource.readFromLocal().map {
            ApiCallState.Success(result = ApiListResponse(data = it))
        }
    }

    override suspend fun getRemote(param: AnyParam): Response<ApiListResponse<CityDataModel>> {
        return cityRemoteDataSource.getCityList()
    }

    override suspend fun saveRemote(response: ApiListResponse<CityDataModel>?) {
        if (response?.data != null)
            cityLocalDataSource.writeToLocal(response.data)
    }

    override fun getCityList(strategy: RepositoryStrategy): Flow<ApiCallState<List<City>>> {
        return getResult(AnyParam(), strategy).map {

            when (it) {
                is ApiCallState.Success ->
                    ApiCallState.Success(result = it.result.data.map { cityDataModel ->
                        CityDataModelMapper().mapToEntity(cityDataModel)
                    })
                is ApiCallState.Failure -> ApiCallState.Failure(it.error)
            }
        }
    }

}