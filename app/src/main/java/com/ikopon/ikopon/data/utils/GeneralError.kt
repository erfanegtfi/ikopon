package com.ikopon.ikopon.data.utils

import io.vaiyo.domain.utils.UtilsError
import retrofit2.HttpException


data class GeneralError constructor(
    var throwable: Throwable? = null,
    var errorType: ErrorType? = null,
    var message: String? = null,
    var messageRes: Int? = 0,
    var errorBody: ErrorApp? = null
) {
    init {
        if (errorBody != null) {
            if (message == null)
                this.message = errorBody!!.message
        }

        throwable?.let { throwable ->
            if (throwable is HttpException) {
                errorType = when (throwable.code()) {
                    401, 403 -> ErrorType.AUTHORIZED
                    404 -> ErrorType.NOT_FOUND
                    400 -> ErrorType.BAD_REQUEST
                    in 500..598 -> ErrorType.SERVER_ERROR
                    else -> ErrorType.UNKNOWN_REMOTE
                }
                if (errorBody == null)
                    this.errorBody = UtilsError.parseError(throwable.response()?.errorBody())
            } else {
                this.message = throwable.message
            }

            if (message == null)
                this.message = errorBody?.message

            // handle messageRes
        }
    }
}

//fun GeneralError.withError(
//    throwable: Throwable? = null,
//    message: String? = null,
//    errorBody: ErrorApp? = null
//): GeneralError {
//    this.throwable = throwable
//    this.message = message
//    this.errorBody = errorBody
//
//    errorBody?.let {
//        if (message == null)
//            this.message = it.message
//    }
//
//    throwable?.let {
//        if (errorBody == null)
//            if (throwable is HttpException)
//                this.errorBody = UtilsError.parseError(throwable.response()?.errorBody())
//        if (message == null)
//            this.message = errorBody?.message
//    }
//
//    if (throwable is HttpException)
//        errorType = when (throwable.code()) {
//            401, 403 -> ErrorType.AUTHORIZED
//            404 -> ErrorType.NOT_FOUND
//            400 -> ErrorType.BAD_REQUEST
//            in 500..598 -> ErrorType.SERVER_ERROR
//            else -> ErrorType.UNKNOWN_REMOTE
//        }
//
//    return GeneralError(
//        throwable = this.throwable,
//        errorType = this.errorType,
//        message = this.message,
//        errorBody = this.errorBody,
//    )
//}