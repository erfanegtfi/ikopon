package com.ikopon.ikopon.data.dataSource.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ikopon.ikopon.data.dataSource.local.room.dao.CityDao
import com.ikopon.ikopon.model.City


@Database(entities = [City::class], version = DatabaseMigrations.DB_VERSION)
abstract class IkoponDatabase : RoomDatabase() {

    abstract fun getCityDao(): CityDao

    companion object {
        const val DB_NAME = "ikopon_database"

        @Volatile
        private var INSTANCE: IkoponDatabase? = null

        fun getInstance(context: Context): IkoponDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, IkoponDatabase::class.java, DB_NAME
                ).addMigrations(*DatabaseMigrations.MIGRATIONS).build()

                INSTANCE = instance
                return instance
            }
        }

    }
}