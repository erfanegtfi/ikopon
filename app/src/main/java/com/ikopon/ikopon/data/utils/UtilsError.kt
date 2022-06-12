package com.ikopon.ikopon.data.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ikopon.ikopon.model.base.ApiBaseResponse
import okhttp3.ResponseBody
import org.json.JSONObject


object UtilsError {

    fun parseError(responseBody: ResponseBody?): ApiBaseResponse? {
        val gson: Gson = GsonBuilder().setPrettyPrinting().create();
        val jObjError = JSONObject(responseBody?.string()?:"{}")

        return gson.fromJson(jObjError.toString(), ApiBaseResponse::class.java)
    }
}