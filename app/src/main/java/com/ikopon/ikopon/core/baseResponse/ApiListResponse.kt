package com.ikopon.ikopon.core.baseResponse;

import com.google.gson.annotations.SerializedName;


class ApiListResponse<T>(
    @SerializedName("data") var data: List<T>
) : ApiBaseResponse() {


}