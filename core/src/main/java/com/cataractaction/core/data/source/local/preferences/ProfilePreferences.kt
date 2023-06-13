package com.cataractaction.core.data.source.local.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProfilePreferences @Inject constructor(private val dataStore: DataStore<Preferences>) {
    suspend fun saveAuthProfile(name: String) {
        dataStore.edit { preferences ->
            preferences[NAME] = name
        }
    }

    fun getAuthName(): Flow<String?> = dataStore.data.map { preferences ->
        preferences[NAME]
    }

    companion object {
        private val NAME = stringPreferencesKey("name")
    }
}