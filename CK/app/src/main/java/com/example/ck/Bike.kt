package com.example.ck

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color

enum class BikeStatus(val title: String, val color: Color) {
    AVAILABLE("Sẵn sàng", Color(0xFF26B99A)),
    RENTED("Đang thuê", Color(0xFF337AB7)),
    BROKEN("Xe hỏng", Color(0xFFE74C3C))
}

data class Bike(
    val id: String,
    val name: String,
    val description: String = "",
    val status: BikeStatus,
    val customerName: String? = null,
    val customerId: String? = null,
    val startTime: String? = null,
    val returnTime: String? = null,
    val hourlyRate: Double = 12500.0,
    val isPaid: Boolean = false
)

val dummyBikes = mutableStateListOf(
    Bike("1", "Honda Vision", "xe ywasaki", BikeStatus.AVAILABLE),
    Bike("2", "Yamaha AirBlade", "toboki", BikeStatus.RENTED, customerName = "Vãng Lai", customerId = "2", startTime = "23:49:04 - 16/09/2019", returnTime = "23:52:35 - 16/09/2019"),
    Bike("3", "Honda Wave", "tàu", BikeStatus.AVAILABLE),
    Bike("4", "Yamaha Sirius", "xe wave", BikeStatus.AVAILABLE),
    Bike("5", "VinFast Feliz", "hỏng ắc quy", BikeStatus.BROKEN)
)
