package com.ikopon.ikopon.data.dataSource.local.preferences



interface SessionManager {
    fun saveToken(token: String?)
    fun getToken(): String

}