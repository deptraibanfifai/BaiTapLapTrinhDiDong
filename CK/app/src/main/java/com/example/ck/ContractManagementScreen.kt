package com.example.ck

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContractManagementScreen(navController: NavController, filter: String) {
    var viewContractBike by remember { mutableStateOf<Bike?>(null) }
    
    val title = when(filter) {
        "unpaid" -> "Hợp đồng thuê xe đang thực hiện (Chưa thanh lý)"
        "paid" -> "Hợp đồng thuê xe đã thanh lý"
        else -> "Tổng hợp hợp đồng"
    }

    // Lọc danh sách dựa trên trạng thái thuê và trạng thái thanh toán
    val filteredBikes = dummyBikes.filter {
        when(filter) {
            "unpaid" -> it.status == BikeStatus.RENTED && !it.isPaid
            "paid" -> it.isPaid
            else -> it.status == BikeStatus.RENTED || it.isPaid
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title, fontSize = 18.sp) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("dashboard") }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).background(Color(0xFFF5F5F5)).padding(16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(2.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth().background(Color(0xFFF9F9F9)).padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("ID", modifier = Modifier.width(40.dp), fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        Text("Xe", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        Text("Thuê lúc", modifier = Modifier.weight(1.5f), fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        if (filter == "paid") {
                            Text("Trả lúc", modifier = Modifier.weight(1.5f), fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        }
                        Text("Khách hàng", modifier = Modifier.weight(1.2f), fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        Text("A", modifier = Modifier.width(40.dp), fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                    HorizontalDivider(color = Color.LightGray)

                    LazyColumn {
                        items(filteredBikes) { bike ->
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(bike.id, modifier = Modifier.width(40.dp), fontSize = 12.sp)
                                Text(bike.name, modifier = Modifier.weight(1f), fontSize = 12.sp)
                                Text(bike.startTime ?: "", modifier = Modifier.weight(1.5f), fontSize = 12.sp)
                                if (filter == "paid") {
                                    Text(bike.returnTime ?: "", modifier = Modifier.weight(1.5f), fontSize = 12.sp)
                                }
                                Text(bike.customerName ?: "", modifier = Modifier.weight(1.2f), fontSize = 12.sp)
                                IconButton(onClick = { viewContractBike = bike }, modifier = Modifier.size(24.dp)) {
                                    Icon(Icons.Default.Search, contentDescription = null, modifier = Modifier.size(16.dp), tint = Color.Gray)
                                }
                            }
                            HorizontalDivider(color = Color(0xFFEEEEEE))
                        }
                    }
                }
            }
        }
    }

    if (viewContractBike != null) {
        ContractDialog(
            bike = viewContractBike!!,
            onDismiss = { viewContractBike = null },
            onComplete = {
                val idx = dummyBikes.indexOf(viewContractBike)
                if (idx != -1) {
                    dummyBikes[idx] = viewContractBike!!.copy(
                        status = BikeStatus.AVAILABLE,
                        isPaid = true
                    )
                }
                viewContractBike = null
            },
            onChangeBike = {
                val idx = dummyBikes.indexOf(viewContractBike)
                if (idx != -1) {
                    dummyBikes[idx] = viewContractBike!!.copy(status = BikeStatus.AVAILABLE)
                }
                viewContractBike = null
                navController.navigate("bike_management/available")
            }
        )
    }
}
