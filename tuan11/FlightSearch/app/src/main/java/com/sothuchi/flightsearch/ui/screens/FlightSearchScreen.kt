package com.example.flightsearch.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
//import com.example.flightsearch.ui.AppViewModelProvider
import com.example.flightsearch.ui.FlightSearchViewModel
import com.sothuchi.flightsearch.AppViewModelProvider


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightSearchScreen(
    modifier: Modifier = Modifier,
    viewModel: FlightSearchViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val searchQuery by viewModel.searchQuery.collectAsState()
    val airports by viewModel.airportListState.collectAsState()
    val selectedAirport by viewModel.selectedAirport.collectAsState()
    val destinations by viewModel.destinationListState.collectAsState()
    val favorites by viewModel.favoriteListState.collectAsState()

    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        // Thanh Tìm Kiếm (SearchBar)
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { viewModel.updateSearchQuery(it) },
            placeholder = { Text("Enter airport code or city name") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { viewModel.updateSearchQuery("") }) {
                        Icon(Icons.Default.Clear, contentDescription = "Clear")
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Điều phối hiển thị dựa theo Trạng thái nhập liệu và Chọn sân bay
        when {
            searchQuery.isBlank() -> {
                FavoriteBody(
                    favorites = favorites,
                    onRemoveFav = { fav ->
                        viewModel.toggleFavorite(fav.departureCode, fav.destinationCode, true)
                    }
                )
            }
            selectedAirport != null -> {
                FlightListBody(
                    departure = selectedAirport!!,
                    destinations = destinations,
                    favorites = favorites,
                    onFavoriteClick = { destCode, isFav ->
                        viewModel.toggleFavorite(selectedAirport!!.iataCode, destCode, isFav)
                    }
                )
            }
            else -> {
                AirportSearchList(
                    airports = airports,
                    onAirportClick = { viewModel.selectAirport(it) }
                )
            }
        }
    }
}

// Hàm FlightCard dùng chung cho cả FavoriteBody và FlightListBody nên bắt buộc phải đặt ở đây
@Composable
fun FlightCard(
    depCode: String, depName: String,
    destCode: String, destName: String,
    isFavorite: Boolean, onFavoriteClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text("DEPART", fontSize = 12.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(depCode, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(depName, fontSize = 14.sp, maxLines = 1)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text("ARRIVE", fontSize = 12.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(destCode, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(destName, fontSize = 14.sp, maxLines = 1)
                }
            }
            IconButton(onClick = onFavoriteClick) {
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Star else Icons.Default.Star,
                    contentDescription = null,
                    tint = if (isFavorite) Color(0xFFFFB300) else Color.LightGray
                )
            }
        }
    }
}