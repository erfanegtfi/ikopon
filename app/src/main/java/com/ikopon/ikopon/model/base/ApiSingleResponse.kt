package com.ikopon.ikopon.model.base;


import com.google.gson.annotations.SerializedName;


class ApiSingleResponse<T>(
    @SerializedName("data") var data: T
) : ApiBaseResponse() {


}
