package com.ikopon.ikopon.model.base;

import com.google.gson.annotations.SerializedName;


class ApiListResponse<T>(
    @SerializedName("data") var data: List<T>
) : ApiBaseResponse() {


}