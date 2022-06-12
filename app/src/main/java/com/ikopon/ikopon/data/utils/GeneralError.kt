package com.ikopon.ikopon.data.utils

import com.ikopon.ikopon.model.base.ApiBaseResponse
import retrofit2.HttpException


data class GeneralError constructor(
    var errorBody: ApiBaseResponse? = null,
    var throwable: Throwable? = null,
    var message: String? = null,
    var messageRes: Int? = 0,
) {
    private var errorType: ErrorType? = null

    init {
        if (errorBody != null) {
            if (message == null)
                this.message = errorBody!!.message.toString()
        } else if (throwable != null) {
            if (throwable is HttpException) {
                errorType = when ((throwable as HttpException).code()) {
                    401, 403 -> ErrorType.AUTHORIZED
                    404 -> ErrorType.NOT_FOUND
                    400 -> ErrorType.BAD_REQUEST
                    in 500..598 -> ErrorType.SERVER_ERROR
                    else -> ErrorType.UNKNOWN_REMOTE
                }
                if (errorBody == null)
                    this.errorBody = UtilsError.parseError((throwable as HttpException).response()?.errorBody())
            } else {
                this.message = throwable!!.message
            }

//            if (message == null)
//                this.message = errorBody?.message.toString()

        }
    }
}