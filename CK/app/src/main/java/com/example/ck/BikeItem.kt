package com.example.ck

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun BikeItem(bike: Bike) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = "Tên xe: ${bike.name}", fontWeight = FontWeight.Bold, color = Color.Black)
                Text(text = "Mã xe: ${bike.id}", color = Color.Gray)
            }
            Text(
                text = bike.status.title,
                color = bike.status.color,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
