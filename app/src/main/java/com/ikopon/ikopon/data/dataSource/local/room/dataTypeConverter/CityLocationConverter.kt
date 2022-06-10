package com.ikopon.ikopon.data.dataSource.local.room.dataTypeConverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.collections.ArrayList

class CityLocationConverter {

    @TypeConverter
    fun fromTrackList(location: ArrayList<Double>): String {
        val type = object : TypeToken<ArrayList<Double>>() {}.type
        return Gson().toJson(location, type)
    }

    @TypeConverter
    fun toTrackList(location: String): ArrayList<Double> {
        val type = object : TypeToken<ArrayList<Double>>() {}.type
        return Gson().fromJson(location, type)
    }

}