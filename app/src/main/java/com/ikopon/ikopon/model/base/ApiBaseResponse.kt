package com.ikopon.ikopon.model.base;

import com.google.gson.annotations.SerializedName;

open class ApiBaseResponse(
    @SerializedName("msg") var message: String? = null,
    @SerializedName("status") var status: Int? = null,
) {
}
