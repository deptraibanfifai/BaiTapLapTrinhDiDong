package com.example.flightsearch.data

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class UserPreferencesRepository(private val dataStore: DataStore<Preferences>) {
    private object PreferencesKeys {
        val SEARCH_TEXT = stringPreferencesKey("search_text")
    }

    val searchText: Flow<String> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.e("Preferences", "Error reading preferences.", exception)
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[PreferencesKeys.SEARCH_TEXT] ?: ""
        }

    suspend fun saveSearchText(text: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.SEARCH_TEXT] = text
        }
    }
}