package com.ikopon.ikopon.data.repository.base

import com.ikopon.ikopon.data.utils.GeneralError
import com.ikopon.ikopon.model.base.ApiBaseResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import retrofit2.Response

@ExperimentalCoroutinesApi
abstract class StrategyFlowRepository<T, PARAM : Param>() : BaseFlowRepository<T, PARAM> {


    override fun getResult(
        param: PARAM, strategy: RepositoryStrategy
    ): Flow<ApiCallState<T>> = when (strategy) {
        is RepositoryStrategy.Remote -> getRemoteEmission(param)
        is RepositoryStrategy.OfflineFirst -> getOfflineFirst(param)
        is RepositoryStrategy.Local -> getLocalEmission(param)
        else -> getRemoteEmission(param)
    }


    /**
     * Provides data based on OfflineFirst strategy, first emits local data,
     * then goes for remote data and if it's available it will save data into db
     * and again delivers the local data, respecting SingleSourceOfTruth.
     */
    private fun getOfflineFirst(param: PARAM) = flow {
        emit(getLocal(param).first())


        val response = apiCall(param)
        if (response is ApiCallState.Success)
            saveRemote(response.data)

        emitAll(getLocal(param))

    }.catch {
        it.printStackTrace()
        emit(ApiCallState.Failure(GeneralError(throwable = it)))
    }

    /**
     * Gets local data, handles [Exception] in between.
     *@return [ResultResponse.Failure] if any [Exception] happens,
     * otherwise it's a type of [ResultResponse.Success].
     */
    private fun getLocalEmission(param: PARAM) = getLocal(param).catch {
        it.printStackTrace()
        emit(ApiCallState.Failure(GeneralError(throwable = it)))
    }


    /**
     * Gets remote data, handles [Exception] in between.
     *@return [ResultResponse.Failure] if any [Exception] happens,
     * otherwise it's a type of [ResultResponse.Success].
     */
    private fun getRemoteEmission(param: PARAM): Flow<ApiCallState<T>> = flow {
        emit(apiCall(param))
    }

    private suspend fun apiCall(param: PARAM): ApiCallState<T> {
        val response = getResult {
            getRemote(param)
        }
        return when (response) {
            is ApiCallState.Success -> ApiCallState.Success(response.data)
            is ApiCallState.Failure -> ApiCallState.Failure(response.error)
        }
    }

    /**
     * Gets local data from provided data source. override it to implement your entity logic.
     */
    abstract fun getLocal(param: PARAM): Flow<ApiCallState<T>>

    /**
     * Gets remote data from provided data source. override it to implement your entity logic.
     */
    abstract suspend fun getRemote(param: PARAM): Response<T>

    /**
     * Saves remote data into local data base.
     */
    abstract suspend fun saveRemote(response: T?)

}


/**
 * Base repository interface for operations that's going to use [Flow].
 */
interface BaseFlowRepository<T, PARAM : Param> {

    /**
     * The main method that's going to be called from clients.
     * @param param contains necessary parameters for the operation.
     * @param strategy decides how data should be fetched.
     */
    fun getResult(param: PARAM, strategy: RepositoryStrategy): Flow<ApiCallState<T>>

}

