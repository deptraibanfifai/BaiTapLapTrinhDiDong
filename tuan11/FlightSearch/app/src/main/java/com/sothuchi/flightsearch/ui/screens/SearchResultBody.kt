package com.example.flightsearch.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sothuchi.flightsearch.data.Airport
import com.example.flightsearch.data.Favorite



@Composable
fun AirportSearchList(
    airports: List<Airport>,
    onAirportClick: (Airport) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(items = airports, key = { it.id }) { airport ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onAirportClick(airport) }
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = airport.iataCode, fontWeight = FontWeight.Bold, modifier = Modifier.width(60.dp))
                Text(text = airport.name, fontSize = 14.sp)
            }
            HorizontalDivider(color = Color.LightGray, thickness = 0.5.dp)
        }
    }
}

@Composable
fun FlightListBody(
    departure: Airport,
    destinations: List<Airport>,
    favorites: List<Favorite>,
    onFavoriteClick: (String, Boolean) -> Unit,
    modifier: Modifier = Modifier // Sửa chữ M thứ hai thành viết thường ở giá trị mặc định nếu cần
) {
    Text(
        text = "Flights from ${departure.iataCode}",
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.titleMedium,
        modifier = modifier.padding(bottom = 8.dp)
    )
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        // SỬA Ở ĐÂY: Thay đổi cách gọi items từ (count = ...) sang (items = ...)
        items(items = destinations, key = { it.id }) { dest ->
            val isFav = favorites.any { it.departureCode == departure.iataCode && it.destinationCode == dest.iataCode }
            FlightCard(
                depCode = departure.iataCode,
                depName = departure.name,
                destCode = dest.iataCode,
                destName = dest.name,
                isFavorite = isFav,
                onFavoriteClick = { onFavoriteClick(dest.iataCode, isFav) }
            )
        }
    }
}