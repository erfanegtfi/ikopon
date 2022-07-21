package com.ikopon.ikopon.core.baseResponse;


import com.google.gson.annotations.SerializedName;


class ApiSingleResponse<T>(
    @SerializedName("data") var data: T
) : ApiBaseResponse()
