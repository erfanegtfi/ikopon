package com.ikopon.ikopon.core.utils

sealed class ApiCallState<out T> {
    data class Success<out T>(val result: T): ApiCallState<T>()
    data class Failure(val error: GeneralError): ApiCallState<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$result]"
            is Failure -> "Error[exception=]"
        }
    }
}