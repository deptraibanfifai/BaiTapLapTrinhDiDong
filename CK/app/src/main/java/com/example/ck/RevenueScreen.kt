package com.example.ck

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RevenueScreen(navController: NavController, type: String) {
    var viewInvoiceBike by remember { mutableStateOf<Bike?>(null) }
    var startDate by remember { mutableStateOf("16/09/2019") }
    var endDate by remember { mutableStateOf("16/09/2019") }
    var bikeNumberFilter by remember { mutableStateOf("") }

    val isDaily = type == "daily"
    val title = if (isDaily) "Doanh thu hôm nay là: 5.000 VNĐ" else "Tra cứu doanh thu tổng hợp"

    // Mock paid data based on dummyBikes
    val paidBikes = dummyBikes.filter { it.isPaid || it.id == "2" } // Forcing one to show for demo

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (isDaily) "Báo cáo doanh thu trong ngày" else "Báo cáo doanh thu tổng hợp", fontSize = 18.sp) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("dashboard") }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFF5F5F5))
                .padding(16.dp)
        ) {
            if (!isDaily) {
                // Search Section for Total Revenue
                Card(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(4.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("Số xe", modifier = Modifier.width(100.dp), fontSize = 14.sp)
                            OutlinedTextField(
                                value = bikeNumberFilter,
                                onValueChange = { bikeNumberFilter = it },
                                modifier = Modifier.fillMaxWidth().height(50.dp)
                            )
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("Từ*", modifier = Modifier.width(100.dp), fontSize = 14.sp)
                            OutlinedTextField(
                                value = startDate,
                                onValueChange = { startDate = it },
                                modifier = Modifier.fillMaxWidth().height(50.dp),
                                trailingIcon = { Icon(Icons.Default.CalendarMonth, null) }
                            )
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("Đến*", modifier = Modifier.width(100.dp), fontSize = 14.sp)
                            OutlinedTextField(
                                value = endDate,
                                onValueChange = { endDate = it },
                                modifier = Modifier.fillMaxWidth().height(50.dp),
                                trailingIcon = { Icon(Icons.Default.CalendarMonth, null) }
                            )
                        }
                        Button(
                            onClick = { },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1ABC9C)),
                            shape = RoundedCornerShape(4.dp),
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        ) {
                            Text("Xem báo cáo")
                        }
                    }
                }
            }

            Text(
                text = if (isDaily) title else "Doanh thu từ $startDate đến $endDate là 5.000 VNĐ",
                fontSize = 20.sp,
                color = Color.DarkGray,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(2.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Column {
                    // Header bảng
                    Row(
                        modifier = Modifier.fillMaxWidth().background(Color(0xFFF9F9F9)).padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("No", modifier = Modifier.width(60.dp), fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        Text("Xe", modifier = Modifier.width(50.dp), fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        Text("Thời gian", modifier = Modifier.weight(1.5f), fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        Text("Số tiền", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        Text("Bằng chữ", modifier = Modifier.weight(1.5f), fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        Text("A", modifier = Modifier.width(40.dp), fontWeight = FontWeight.Bold, fontSize = 12.sp, textAlign = TextAlign.Center)
                    }
                    HorizontalDivider(color = Color.LightGray)

                    LazyColumn {
                        items(paidBikes) { bike ->
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("SB-29", modifier = Modifier.width(60.dp), fontSize = 11.sp, color = Color(0xFF1ABC9C))
                                Box(
                                    modifier = Modifier.size(24.dp).clip(CircleShape).background(Color(0xFF1ABC9C)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(bike.id, color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                                }
                                Spacer(modifier = Modifier.width(26.dp))
                                Text(bike.returnTime ?: "N/A", modifier = Modifier.weight(1.5f), fontSize = 11.sp)
                                Text("5.000", modifier = Modifier.weight(1f), fontSize = 11.sp)
                                Text("Năm ngàn đồng", modifier = Modifier.weight(1.5f), fontSize = 11.sp)
                                IconButton(onClick = { viewInvoiceBike = bike }, modifier = Modifier.size(24.dp)) {
                                    Icon(Icons.Default.Visibility, null, modifier = Modifier.size(16.dp), tint = Color.Gray)
                                }
                            }
                            HorizontalDivider(color = Color(0xFFEEEEEE))
                        }
                    }
                }
            }
        }
    }

    if (viewInvoiceBike != null) {
        InvoiceDialog(bike = viewInvoiceBike!!, onDismiss = { viewInvoiceBike = null })
    }
}

@Composable
fun InvoiceDialog(bike: Bike, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { 
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Hoá đơn 29", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(bike.returnTime ?: "", fontSize = 12.sp, color = Color.Gray)
            }
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Cửa hàng", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        Text("Xe đạp điện", fontSize = 12.sp)
                        Text("Tổ 2 Phường Quyết Tâm - TP Sơn La", fontSize = 11.sp, color = Color.Gray)
                        Text("Phone: 02126296789", fontSize = 11.sp, color = Color.Gray)
                    }
                    Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End) {
                        Text("Khách hàng", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        Text(bike.customerName ?: "Vãng Lai", fontSize = 12.sp)
                        Text("Sơn La", fontSize = 11.sp, color = Color.Gray)
                    }
                }
                
                HorizontalDivider()
                
                Row(modifier = Modifier.fillMaxWidth().background(Color(0xFFF9F9F9)).padding(8.dp)) {
                    Text("Nội dung", modifier = Modifier.weight(2f), fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    Text("Số giờ", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    Text("Thành tiền", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold, fontSize = 12.sp, textAlign = TextAlign.End)
                }
                
                Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                    Text("Thuê xe số ${bike.id}", modifier = Modifier.weight(2f), fontSize = 12.sp)
                    Text("4 phút", modifier = Modifier.weight(1f), fontSize = 12.sp)
                    Text("5.000 VNĐ", modifier = Modifier.weight(1f), fontSize = 12.sp, textAlign = TextAlign.End)
                }
                
                Spacer(modifier = Modifier.height(20.dp))
                
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text("Khách hàng", modifier = Modifier.weight(1f), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    Text("Người lập hoá đơn", modifier = Modifier.weight(1f), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }
                Row(modifier = Modifier.fillMaxWidth().padding(top = 40.dp)) {
                    Text("", modifier = Modifier.weight(1f))
                    Text("Xe đạp điện", modifier = Modifier.weight(1f), textAlign = TextAlign.Center, fontSize = 12.sp)
                }
            }
        },
        confirmButton = {
            Button(onClick = onDismiss, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1ABC9C))) {
                Text("Đóng")
            }
        }
    )
}
