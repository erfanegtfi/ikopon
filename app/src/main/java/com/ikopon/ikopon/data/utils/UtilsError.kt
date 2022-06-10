package io.vaiyo.domain.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ikopon.ikopon.data.utils.ErrorApp
import okhttp3.ResponseBody
import org.json.JSONObject


object UtilsError {

    fun parseError(responseBody: ResponseBody?): ErrorApp? {
        val gson: Gson = GsonBuilder().setPrettyPrinting().create();
        val jObjError = JSONObject(responseBody?.string()?:"{}")

        return gson.fromJson(jObjError.toString(), ErrorApp::class.java)
    }
}