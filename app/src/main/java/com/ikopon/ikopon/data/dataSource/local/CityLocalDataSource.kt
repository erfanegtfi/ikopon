package com.ikopon.ikopon.data.dataSource.local

import com.ikopon.ikopon.data.dataSource.local.room.dao.CityDao
import com.ikopon.ikopon.model.City
import kotlinx.coroutines.flow.Flow

class CityLocalDataSource constructor(private val cityDao: CityDao) : LocalDataSource<List<City>> {

    override fun readFromLocal(): Flow<List<City>> {
        return cityDao.getAllCities()
    }

    override fun writeToLocal(data: List<City>) {
        cityDao.insertCities(data)
    }

}