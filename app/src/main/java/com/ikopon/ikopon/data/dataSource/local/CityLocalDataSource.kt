package com.ikopon.ikopon.data.dataSource.local

import com.ikopon.ikopon.data.dataSource.local.room.dao.CityDao
import com.ikopon.ikopon.data.model.CityDataModel
import com.ikopon.ikopon.domain.entities.City
import kotlinx.coroutines.flow.Flow

class CityLocalDataSource constructor(private val cityDao: CityDao) : LocalDataSource<List<CityDataModel>> {

    override fun readFromLocal(): Flow<List<CityDataModel>> {
        return cityDao.getAllCities()
    }

    override fun writeToLocal(data: List<CityDataModel>) {
        cityDao.insertCities(data)
    }

}