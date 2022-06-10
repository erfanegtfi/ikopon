package com.ikopon.ikopon.data.dataSource.local.preferences

import com.ikopon.ikopon.data.dataSource.local.preferences.PreferencesConstants.TOKEN_KEY

class SessionManagerImp constructor(
    private val session: Session
): SessionManager {

    override  fun saveToken(token: String?) {
        session.setPreferenceValue(TOKEN_KEY, token)
    }

    override fun getToken(): String = session.getPreferenceValue(TOKEN_KEY, "")


}

