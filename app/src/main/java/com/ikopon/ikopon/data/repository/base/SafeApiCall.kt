package com.ikopon.ikopon.data.repository.base

import android.util.Log
import com.ikopon.ikopon.data.utils.ErrorApp
import com.ikopon.ikopon.data.utils.GeneralError
import io.vaiyo.domain.utils.exception.NetworkConnectionException
import com.ikopon.ikopon.data.utils.UtilsError
import com.ikopon.ikopon.model.base.ApiBaseResponse
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.net.SocketTimeoutException


suspend fun <T> getResult(call: suspend () -> Response<T>): ApiCallState<T> {
    try {
        val response = call.invoke()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null)
                return onResponse(body)
        }
        return error(response.errorBody())
    } catch (e: Throwable) {
        e.printStackTrace()
        return error(e)
    }
}

private fun <T> error(throwable: Throwable): ApiCallState<T> {
    val apiCallResult: ApiCallState<T>
    if (throwable is HttpException) {
        apiCallResult = ApiCallState.Failure(GeneralError(throwable = throwable))
    } else if (throwable is SocketTimeoutException) {
        apiCallResult = ApiCallState.Failure(GeneralError(throwable = throwable))
    } else if (throwable is NetworkConnectionException) {//|| throwable instanceof IOException
        apiCallResult = ApiCallState.Failure(GeneralError(throwable = throwable))
    } else {
        Log.e("errorrrrrr ", throwable.message ?: "");
        apiCallResult = ApiCallState.Failure(GeneralError(throwable = throwable))
    }

    return apiCallResult
}

private fun <T> error(responseBody: ResponseBody?): ApiCallState<T> {

    val errorApp: ApiBaseResponse? = UtilsError.parseError(responseBody)

    return ApiCallState.Failure(GeneralError(errorBody = errorApp))
}


fun <T> onResponse(response: T): ApiCallState<T> {
    return if (response is ApiBaseResponse) {
        if (response.status == 200)
            ApiCallState.Success(response)
        else
            ApiCallState.Failure(GeneralError(errorBody = response))
    } else ApiCallState.Success(response)
}