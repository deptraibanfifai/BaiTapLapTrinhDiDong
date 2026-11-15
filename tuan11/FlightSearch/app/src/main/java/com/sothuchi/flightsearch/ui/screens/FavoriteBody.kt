package com.example.flightsearch.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.flightsearch.data.Favorite

@Composable
fun FavoriteBody(
    favorites: List<Favorite>,
    onRemoveFav: (Favorite) -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = "Favorite routes",
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.titleMedium,
        modifier = modifier.padding(bottom = 8.dp)
    )
    if (favorites.isEmpty()) {
        Text("No favorite flights yet.", color = Color.Gray, modifier = Modifier.padding(top = 8.dp))
    } else {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(favorites, key = { it.id }) { fav ->
                FlightCard(
                    depCode = fav.departureCode,
                    depName = "Saved Route",
                    destCode = fav.destinationCode,
                    destName = "",
                    isFavorite = true,
                    onFavoriteClick = { onRemoveFav(fav) }
                )
            }
        }
    }
}