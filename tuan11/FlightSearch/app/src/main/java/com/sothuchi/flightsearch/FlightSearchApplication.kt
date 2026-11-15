package com.sothuchi.flightsearch

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.flightsearch.data.UserPreferencesRepository

// Khởi tạo DataStore để lưu trạng thái tìm kiếm
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "flight_search_preferences")

class FlightSearchApplication : Application() {

    // Khởi tạo lazy cho Database và Repository mà không dùng hậu tố .data hay .ui
    val database: FlightDatabase by lazy {
        FlightDatabase.getDatabase(this)
    }

    val userPreferencesRepository: UserPreferencesRepository by lazy {
        UserPreferencesRepository(dataStore)
    }

    override fun onCreate() {
        super.onCreate()
    }
}