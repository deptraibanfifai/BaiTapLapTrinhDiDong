package com.example.flightsearch.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sothuchi.flightsearch.data.Airport
import com.example.flightsearch.data.Favorite
import com.example.flightsearch.data.FlightDao
import com.example.flightsearch.data.UserPreferencesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin. collections.emptyList


class FlightSearchViewModel(
    private val flightDao: FlightDao,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    // Sân bay đang được chọn để xem danh sách chuyến bay đi
    private val _selectedAirport = MutableStateFlow<Airport?>(null)
    val selectedAirport: StateFlow<Airport?> = _selectedAirport.asStateFlow()

    init {
        // Đọc lại từ khóa cũ từ DataStore khi khởi chạy app
        viewModelScope.launch {
            userPreferencesRepository.searchText.collect { text ->
                _searchQuery.value = text
            }
        }
    }

    // Danh sách kết quả tìm kiếm sân bay dựa trên từ khóa
    @OptIn(ExperimentalCoroutinesApi::class)
    val airportListState: StateFlow<List<Airport>> = _searchQuery
        .flatMapLatest { query ->
            if (query.isBlank()) {
                flowOf(emptyList())
            } else {
                flightDao.getAirportsBySearch("%$query%")
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Danh sách tất cả điểm đến khả dụng cho sân bay đang chọn
    @OptIn(ExperimentalCoroutinesApi::class)
    val destinationListState: StateFlow<List<Airport>> = _selectedAirport
        .flatMapLatest { airport ->
            if (airport == null) flowOf(emptyList())
            else flightDao.getAllDestinations(airport.iataCode)
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Toàn bộ danh sách chặng bay yêu thích
    val favoriteListState: StateFlow<List<Favorite>> = flightDao.getAllFavorites()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        _selectedAirport.value = null // Reset sân bay đã chọn nếu gõ từ khóa mới
        viewModelScope.launch {
            userPreferencesRepository.saveSearchText(query)
        }
    }

    fun selectAirport(airport: Airport) {
        _selectedAirport.value = airport
    }

    fun clearSelectedAirport() {
        _selectedAirport.value = null
    }

    fun toggleFavorite(departureCode: String, destinationCode: String, isFav: Boolean) {
        viewModelScope.launch {
            if (isFav) {
                flightDao.deleteFavorite(Favorite(departureCode = departureCode, destinationCode = destinationCode))
            } else {
                flightDao.insertFavorite(Favorite(departureCode = departureCode, destinationCode = destinationCode))
            }
        }
    }
}