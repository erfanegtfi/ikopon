package com.ikopon.ikopon.data.dataSource.local.room.dao

import androidx.room.*
import com.ikopon.ikopon.model.City
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {

    /**
     * Inserts [posts] into the [Post.TABLE_NAME] table.
     * Duplicate values are replaced in the table.
     * @param posts Posts
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCities(posts: List<City>)


    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateCities(track: List<City>)


    /**
     * Fetches all the posts from the [Post.TABLE_NAME] table.
     * @return [Flow]
     */
    @Query("SELECT * FROM ${City.TABLE_NAME}")
    fun getAllCities(): Flow<List<City>>

    @Query("DELETE FROM ${City.TABLE_NAME}")
    fun deleteAllCities()

}