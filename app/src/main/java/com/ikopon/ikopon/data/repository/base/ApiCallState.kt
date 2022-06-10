package com.ikopon.ikopon.data.repository.base

import com.ikopon.ikopon.data.utils.GeneralError


sealed class ApiCallState<out T> {
    data class Success<out T>(val data: T): ApiCallState<T>()
    data class Failure(val error: GeneralError): ApiCallState<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Failure -> "Error[exception=]"
        }
    }
}