package com.example.ck

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
fun ProfileScreen(navController: NavController) {
    var fullName by remember { mutableStateOf("Xe đạp điện") }
    var username by remember { mutableStateOf("xedapdien") }
    var birthDate by remember { mutableStateOf("04/04/1988") }
    var email by remember { mutableStateOf("test@live.com") }
    var phone by remember { mutableStateOf("0") }
    var gender by remember { mutableStateOf("Nam") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cập nhật thông tin cá nhân", fontSize = 18.sp) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    OutlinedTextField(
                        value = "", onValueChange = {},
                        placeholder = { Text("Search for...", fontSize = 12.sp) },
                        modifier = Modifier.width(150.dp).padding(end = 8.dp).height(45.dp),
                        trailingIcon = { Text("Go!", color = Color.Gray, modifier = Modifier.padding(end = 8.dp)) },
                        shape = RoundedCornerShape(20.dp),
                        colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = Color.White)
                    )
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
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                shape = RoundedCornerShape(4.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Thông tin cán bộ", fontSize = 22.sp, fontWeight = FontWeight.Normal)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("nhân viên", fontSize = 16.sp, color = Color.Gray)
                    }
                    HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp), color = Color(0xFFEEEEEE))

                    // Các trường nhập liệu giống ảnh
                    ProfileRow("Tên đầy đủ *", fullName) { fullName = it }
                    ProfileRow("Tên đăng nhập *", username) { username = it }
                    ProfileRow("Ngày sinh *", birthDate) { birthDate = it }
                    ProfileRow("Dân tộc *", "Kinh") { }
                    ProfileRow("Mật khẩu", "********") { }
                    ProfileRow("Xác nhận mật khẩu", "********") { }
                    ProfileRow("Phòng ban *", "Kỹ thuật") { }
                    ProfileRow("Chức danh *", "Nhân viên") { }
                    ProfileRow("Địa chỉ", "") { }
                    
                    // Giới tính
                    Row(modifier = Modifier.padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text("Giới tính", modifier = Modifier.width(120.dp), fontSize = 14.sp, color = Color.DarkGray)
                        Row {
                            Button(
                                onClick = { gender = "Nam" },
                                colors = ButtonDefaults.buttonColors(containerColor = if (gender == "Nam") Color(0xFF337AB7) else Color.LightGray),
                                shape = RoundedCornerShape(topStart = 4.dp, bottomStart = 4.dp),
                                modifier = Modifier.height(35.dp)
                            ) { Text("Nam", fontSize = 12.sp) }
                            Button(
                                onClick = { gender = "Nữ" },
                                colors = ButtonDefaults.buttonColors(containerColor = if (gender == "Nữ") Color(0xFF337AB7) else Color.LightGray),
                                shape = RoundedCornerShape(topEnd = 4.dp, bottomEnd = 4.dp),
                                modifier = Modifier.height(35.dp)
                            ) { Text("Nữ", fontSize = 12.sp) }
                        }
                    }

                    ProfileRow("Di động *", phone) { phone = it }
                    ProfileRow("Email *", email) { email = it }

                    Spacer(modifier = Modifier.height(24.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.align(Alignment.CenterHorizontally)) {
                        Button(onClick = { }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF337AB7))) { Text("Cancel") }
                        Button(onClick = { }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF337AB7))) { Text("Reset") }
                        Button(onClick = { navController.popBackStack() }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF26B99A))) { Text("Lưu lại") }
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileRow(label: String, value: String, onValueChange: (String) -> Unit) {
    Row(
        modifier = Modifier.padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label, modifier = Modifier.width(120.dp), fontSize = 14.sp, color = Color.DarkGray)
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth().height(50.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = Color.LightGray)
        )
    }
}
