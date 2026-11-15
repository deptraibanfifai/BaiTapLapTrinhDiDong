package com.example.ck

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreProfileScreen(navController: NavController) {
    var storeName by remember { mutableStateOf("Xe đạp điện") }
    var storeId by remember { mutableStateOf("3") }
    var taxId by remember { mutableStateOf("0") }
    var address by remember { mutableStateOf("Tổ 2 Phường Quyết Tâm - TP Sơn La - Tỉnh Sơn La") }
    var representative by remember { mutableStateOf("Phan Văn Liêm") }
    var phone by remember { mutableStateOf("02126296789") }
    var email by remember { mutableStateOf("phanvanliem@live.com") }
    var website by remember { mutableStateOf("https://svtb.com.vn") }
    var status by remember { mutableStateOf("Hoạt động") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Thông tin công ty, cửa hàng", fontSize = 18.sp) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
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
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Thông báo
            Card(
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1ABC9C)),
                shape = RoundedCornerShape(4.dp)
            ) {
                Row(
                    modifier = Modifier.padding(12.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Thông báo: Đăng nhập thành công", color = Color.White, fontSize = 14.sp)
                    Text("x", color = Color.White.copy(alpha = 0.5f), modifier = Modifier.clickable { })
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(4.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    // Logo section
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Logo*", fontSize = 14.sp, color = Color.Gray, modifier = Modifier.align(Alignment.Start))
                        Spacer(modifier = Modifier.height(8.dp))
                        Box(
                            modifier = Modifier
                                .size(150.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF3498DB))
                                .border(4.dp, Color.White, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("GO", color = Color.White, fontSize = 40.sp, fontWeight = FontWeight.Bold)
                        }
                    }

                    StoreInputField("Tên cửa hàng*", storeName) { storeName = it }
                    StoreInputField("Mã số cửa hàng*", storeId, enabled = false) { storeId = it }
                    StoreInputField("Mã số thuế*", taxId) { taxId = it }
                    StoreInputField("Địa chỉ*", address) { address = it }
                    StoreInputField("Đại diện*", representative) { representative = it }
                    StoreInputField("Điện thoại*", phone) { phone = it }
                    
                    // Phân loại (Dropdown simulation)
                    Column(modifier = Modifier.padding(vertical = 8.dp)) {
                        Text("Phân loại*", fontSize = 14.sp, color = Color.Gray)
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(45.dp)
                                .border(1.dp, Color.LightGray, RoundedCornerShape(4.dp))
                                .padding(horizontal = 12.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Text("Doanh nghiệp", fontSize = 14.sp)
                        }
                    }

                    // Hiện trạng
                    Row(
                        modifier = Modifier.padding(vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Hiện trạng", modifier = Modifier.width(100.dp), fontSize = 14.sp, color = Color.Gray)
                        Row {
                            Button(
                                onClick = { status = "Hoạt động" },
                                colors = ButtonDefaults.buttonColors(containerColor = if(status == "Hoạt động") Color(0xFF337AB7) else Color.LightGray),
                                shape = RoundedCornerShape(topStart = 4.dp, bottomStart = 4.dp),
                                modifier = Modifier.height(35.dp)
                            ) { Text("Hoạt động", fontSize = 12.sp) }
                            Button(
                                onClick = { status = "Nghỉ" },
                                colors = ButtonDefaults.buttonColors(containerColor = if(status == "Nghỉ") Color(0xFF337AB7) else Color.LightGray),
                                shape = RoundedCornerShape(topEnd = 4.dp, bottomEnd = 4.dp),
                                modifier = Modifier.height(35.dp)
                            ) { Text("Nghỉ", fontSize = 12.sp) }
                        }
                    }

                    StoreInputField("Email*", email) { email = it }
                    StoreInputField("Trang web*", website) { website = it }
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(
                        onClick = { navController.popBackStack() },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1ABC9C)),
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier.align(Alignment.CenterHorizontally).width(120.dp)
                    ) {
                        Text("Lưu lại")
                    }
                }
            }
        }
    }
}

@Composable
fun StoreInputField(label: String, value: String, enabled: Boolean = true, onValueChange: (String) -> Unit) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(label, fontSize = 14.sp, color = Color.Gray)
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth().height(50.dp),
            enabled = enabled,
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = if(enabled) Color.White else Color(0xFFEEEEEE),
                focusedContainerColor = Color.White
            )
        )
    }
}
