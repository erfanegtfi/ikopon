package com.ikopon.ikopon.data.dataSource.local.room

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ikopon.ikopon.data.model.CityDataModel

object DatabaseMigrations {
    const val DB_VERSION = 1

    val MIGRATIONS: Array<Migration>
        get() = arrayOf<Migration>(
            migrations()
        )

    private fun migrations(): Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE ${CityDataModel.TABLE_NAME} ADD COLUMN body TEXT")
        }
    }
}
