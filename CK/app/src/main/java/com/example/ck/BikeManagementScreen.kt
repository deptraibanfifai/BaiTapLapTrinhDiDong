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
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.*
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
fun BikeManagementScreen(navController: NavController, filter: String) {
    var rentingBike by remember { mutableStateOf<Bike?>(null) }
    var viewContractBike by remember { mutableStateOf<Bike?>(null) }
    var editingBike by remember { mutableStateOf<Bike?>(null) }

    val title = when (filter) {
        "rented" -> "Xe đang cho thuê"
        "available" -> "Xe đang sẵn sàng phục vụ"
        "broken" -> "Danh sách xe hỏng"
        else -> "Danh sách xe của cửa hàng"
    }

    val filteredBikes = dummyBikes.filter {
        when (filter) {
            "rented" -> it.status == BikeStatus.RENTED
            "available" -> it.status == BikeStatus.AVAILABLE
            "broken" -> it.status == BikeStatus.BROKEN
            else -> true
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("$title (${filteredBikes.size})", fontSize = 18.sp) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("dashboard") }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1ABC9C)),
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text(if (filter == "available") "Xe trống" else "Thêm mới")
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
            // Thanh công cụ Search/Filter
            Card(
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(2.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Show ", fontSize = 12.sp)
                        Box(modifier = Modifier.border(1.dp, Color.LightGray).padding(horizontal = 8.dp, vertical = 4.dp)) {
                            Text("10", fontSize = 12.sp)
                        }
                        Text(" entries", fontSize = 12.sp)
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Search: ", fontSize = 12.sp)
                        Box(modifier = Modifier.width(120.dp).height(30.dp).border(1.dp, Color.LightGray))
                    }
                }
            }

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
                        Text("Xe", modifier = Modifier.width(50.dp), fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        Text("Mô tả", modifier = Modifier.weight(1.5f), fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        if (filter == "all") {
                            Text("Trạng thái", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        } else if (filter == "rented") {
                            Text("Cho thuê lúc", modifier = Modifier.weight(1.5f), fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        }
                        Text("A", modifier = Modifier.width(50.dp), fontWeight = FontWeight.Bold, fontSize = 12.sp, textAlign = TextAlign.Center)
                    }
                    HorizontalDivider(color = Color.LightGray)

                    LazyColumn {
                        items(filteredBikes) { bike ->
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // ID hình tròn xanh
                                Box(
                                    modifier = Modifier.size(24.dp).clip(CircleShape).background(Color(0xFF3498DB)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(bike.id, color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                                }
                                Spacer(modifier = Modifier.width(26.dp))
                                
                                Text(bike.description, modifier = Modifier.weight(1.5f), fontSize = 12.sp, color = Color.Gray)
                                
                                if (filter == "all") {
                                    Card(
                                        colors = CardDefaults.cardColors(containerColor = bike.status.color),
                                        shape = RoundedCornerShape(12.dp),
                                        modifier = Modifier.weight(1f).padding(end = 8.dp)
                                    ) {
                                        Text(
                                            bike.status.title,
                                            color = Color.White,
                                            fontSize = 10.sp,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)
                                        )
                                    }
                                } else if (filter == "rented") {
                                    Text(bike.startTime ?: "N/A", modifier = Modifier.weight(1.5f), fontSize = 11.sp)
                                }

                                // Cột hành động (A)
                                IconButton(
                                    onClick = {
                                        when {
                                            filter == "rented" || bike.status == BikeStatus.RENTED -> viewContractBike = bike
                                            filter == "available" -> rentingBike = bike
                                            else -> editingBike = bike
                                        }
                                    },
                                    modifier = Modifier.size(24.dp)
                                ) {
                                    val icon = when {
                                        filter == "rented" || bike.status == BikeStatus.RENTED -> Icons.Default.Search
                                        filter == "available" -> Icons.AutoMirrored.Filled.ExitToApp // Icon cho thuê
                                        else -> Icons.Default.Edit
                                    }
                                    Icon(icon, null, modifier = Modifier.size(16.dp), tint = Color.DarkGray)
                                }
                            }
                            HorizontalDivider(color = Color(0xFFEEEEEE))
                        }
                    }
                }
            }
        }
    }

    // Dialog Cho thuê xe
    if (rentingBike != null) {
        RentBikeDialog(
            bike = rentingBike!!,
            onDismiss = { rentingBike = null },
            onConfirm = { id, name, start, ret ->
                val idx = dummyBikes.indexOf(rentingBike)
                if (idx != -1) {
                    dummyBikes[idx] = rentingBike!!.copy(
                        status = BikeStatus.RENTED,
                        customerId = id,
                        customerName = name,
                        startTime = start,
                        returnTime = ret,
                        isPaid = false
                    )
                }
                rentingBike = null
            }
        )
    }

    // Dialog Xem Hợp đồng
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

    // Dialog Sửa xe
    if (editingBike != null) {
        BikeEditDialog(
            bike = editingBike,
            onDismiss = { editingBike = null },
            onSave = { name: String, desc: String, isBroken: Boolean ->
                val idx = dummyBikes.indexOf(editingBike)
                if (idx != -1) {
                    dummyBikes[idx] = editingBike!!.copy(
                        name = name,
                        description = desc,
                        status = if (isBroken) BikeStatus.BROKEN else BikeStatus.AVAILABLE
                    )
                }
                editingBike = null
            }
        )
    }
}

@Composable
fun RentBikeDialog(bike: Bike, onDismiss: () -> Unit, onConfirm: (String, String, String, String) -> Unit) {
    var customerId by remember { mutableStateOf("") }
    var customerName by remember { mutableStateOf("") }
    var startTime by remember { mutableStateOf("23:48:34 - 16/09/2024") }
    var returnTime by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Cho thuê xe ${bike.id}", fontSize = 18.sp) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Box(modifier = Modifier.fillMaxWidth().background(Color(0xFFFFF3CD)).padding(12.dp).border(1.dp, Color(0xFFFFEEBA))) {
                    Text("Chú ý: Để mặc định nếu không muốn nhập mã khách hàng!", color = Color(0xFF856404), fontSize = 12.sp)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Mã khách hàng:*", modifier = Modifier.width(120.dp), fontSize = 14.sp)
                    OutlinedTextField(value = customerId, onValueChange = { customerId = it }, modifier = Modifier.fillMaxWidth().height(50.dp))
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Tên khách hàng:*", modifier = Modifier.width(120.dp), fontSize = 14.sp)
                    OutlinedTextField(value = customerName, onValueChange = { customerName = it }, modifier = Modifier.fillMaxWidth().height(50.dp))
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Bắt đầu lúc:*", modifier = Modifier.width(120.dp), fontSize = 14.sp)
                    OutlinedTextField(value = startTime, onValueChange = { startTime = it }, modifier = Modifier.fillMaxWidth().height(50.dp))
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Trả lúc:*", modifier = Modifier.width(120.dp), fontSize = 14.sp)
                    OutlinedTextField(value = returnTime, onValueChange = { returnTime = it }, modifier = Modifier.fillMaxWidth().height(50.dp))
                }
            }
        },
        confirmButton = {
            Button(onClick = { onConfirm(customerId, customerName, startTime, returnTime) }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1ABC9C)), shape = RoundedCornerShape(4.dp)) {
                Text("Đồng ý cho thuê")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1ABC9C)), shape = RoundedCornerShape(4.dp)) {
                Text("Thôi")
            }
        }
    )
}

@Composable
fun ContractDialog(bike: Bike, onDismiss: () -> Unit, onComplete: () -> Unit, onChangeBike: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Thông tin chi tiết hợp đồng thuê xe", fontSize = 18.sp) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                ContractDetailRow("Khách hàng:", bike.customerName ?: "N/A")
                ContractDetailRow("Mã khách hàng:", bike.customerId ?: "N/A")
                ContractDetailRow("Thuê xe:", "Xe số ${bike.id}")
                ContractDetailRow("Hình thức:", "Tính giờ")
                ContractDetailRow("Thuê lúc:", bike.startTime ?: "N/A")
                ContractDetailRow("Trả lúc:", bike.returnTime ?: "N/A")
                ContractDetailRow("Giá thuê:", "12,500 VNĐ / giờ")
                ContractDetailRow("Thành tiền:", "12,500 VNĐ")
            }
        },
        confirmButton = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = onComplete, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF26B99A)), modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(4.dp)) {
                    Text("Xác nhận trả xe và thanh toán", fontSize = 12.sp)
                }
                Button(onClick = onChangeBike, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF337AB7)), modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(4.dp)) {
                    Text("Đổi xe", fontSize = 12.sp)
                }
            }
        },
        dismissButton = {
            Button(onClick = onDismiss, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1ABC9C)), shape = RoundedCornerShape(4.dp)) {
                Text("Quay về")
            }
        }
    )
}

@Composable
fun ContractDetailRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
        Text(label, modifier = Modifier.width(120.dp), fontSize = 14.sp, color = Color.Gray)
        Text(value, fontSize = 14.sp, fontWeight = FontWeight.Medium)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BikeEditDialog(bike: Bike?, onDismiss: () -> Unit, onSave: (String, String, Boolean) -> Unit) {
    var name by remember { mutableStateOf(bike?.name ?: "") }
    var desc by remember { mutableStateOf(bike?.description ?: "") }
    var isBroken by remember { mutableStateOf(bike?.status == BikeStatus.BROKEN) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (bike == null) "Thêm xe mới" else "Cập nhật thông tin xe") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Tên xe *") }, modifier = Modifier.fillMaxWidth())
                OutlinedTextField(value = desc, onValueChange = { desc = it }, label = { Text("Mô tả") }, modifier = Modifier.fillMaxWidth(), minLines = 2)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = isBroken, onCheckedChange = { isBroken = it })
                    Text("Xe hỏng")
                }
            }
        },
        confirmButton = {
            Button(onClick = { onSave(name, desc, isBroken) }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1ABC9C))) {
                Text("Lưu lại")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Hủy") }
        }
    )
}
