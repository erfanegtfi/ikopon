package com.ikopon.ikopon.data.dataSource.local

import kotlinx.coroutines.flow.Flow


interface LocalDataSource<T> {

    fun readFromLocal(): Flow<T>
    fun writeToLocal(data: T)

}