package com.ikopon.ikopon.core.baseResponse;

import com.google.gson.annotations.SerializedName;

open class ApiBaseResponse(
    @SerializedName("message") var message: Object? = null,
    @SerializedName("show_type") var show_type: String? = null,
    @SerializedName("http_code") var http_code: Int? = null,
    @SerializedName("success") var success: Boolean? = null,
    @SerializedName("total") var total: Int? = null,
//    @SerializedName("status") var status: Int? = null,
){


}
