package com.ikopon.ikopon.data.utils

import com.google.gson.annotations.SerializedName


data class ErrorApp(
    @SerializedName("status")
    var status: Int? = 500,
    @SerializedName("error")
    val error: String? = null,
    @SerializedName("message")
    val message: String? = null,
    )
