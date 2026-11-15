package com.sothuchi.flightsearch

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.flightsearch.ui.FlightSearchViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FlightSearchApplication)
            FlightSearchViewModel(
                flightDao = application.database.flightDao(),
                userPreferencesRepository = application.userPreferencesRepository
            )
        }
    }
}