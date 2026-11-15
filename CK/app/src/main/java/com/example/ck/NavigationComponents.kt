package com.example.ck

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SidebarContent(navController: NavController, onItemSelected: () -> Unit) {
    var expandedBike by remember { mutableStateOf(false) }
    var expandedContract by remember { mutableStateOf(false) }
    var expandedRevenue by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.size(50.dp).clip(CircleShape).background(Color.White)) {
                Icon(Icons.Default.Person, contentDescription = null, modifier = Modifier.fillMaxSize(), tint = Color.Gray)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text("Xin chào", color = Color.LightGray, fontSize = 12.sp)
                Text("Xe đạp điện", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        Text("TỔNG QUAN", color = Color.Gray, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        
        SidebarItem(Icons.Default.AccountBox, "Hồ sơ cửa hàng", false) {
            navController.navigate("store_profile")
            onItemSelected()
        }

        SidebarItem(Icons.Default.Home, "Quản lý xe thuê", true, expandedBike) {
            expandedBike = !expandedBike
        }
        AnimatedVisibility(visible = expandedBike) {
            Column(modifier = Modifier.padding(start = 32.dp)) {
                SubSidebarItem("Tất cả") { navController.navigate("bike_management/all"); onItemSelected() }
                SubSidebarItem("Xe đang thuê") { navController.navigate("bike_management/rented"); onItemSelected() }
                SubSidebarItem("Xe sẵn sàng") { navController.navigate("bike_management/available"); onItemSelected() }
                SubSidebarItem("Xe hỏng") { navController.navigate("bike_management/broken"); onItemSelected() }
            }
        }

        SidebarItem(Icons.AutoMirrored.Filled.List, "Quản lý hợp đồng", true, expandedContract) {
            expandedContract = !expandedContract
        }
        AnimatedVisibility(visible = expandedContract) {
            Column(modifier = Modifier.padding(start = 32.dp)) {
                SubSidebarItem("Chưa thanh lý") { navController.navigate("contract_management/unpaid"); onItemSelected() }
                SubSidebarItem("Đã thanh lý") { navController.navigate("contract_management/paid"); onItemSelected() }
                SubSidebarItem("Tổng hợp") { navController.navigate("contract_management/summary"); onItemSelected() }
            }
        }

        SidebarItem(Icons.Default.AddCircle, "Báo cáo doanh thu", true, expandedRevenue) {
            expandedRevenue = !expandedRevenue
        }
        AnimatedVisibility(visible = expandedRevenue) {
            Column(modifier = Modifier.padding(start = 32.dp)) {
                SubSidebarItem("Trong ngày") { navController.navigate("revenue_management/daily"); onItemSelected() }
                SubSidebarItem("Tổng hợp") { navController.navigate("revenue_management/total"); onItemSelected() }
            }
        }
    }
}

@Composable
fun SidebarItem(icon: ImageVector, label: String, hasDropdown: Boolean, isExpanded: Boolean = false, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = Color.White, modifier = Modifier.size(20.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(label, color = Color.White, fontSize = 14.sp)
        if (hasDropdown) {
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
fun SubSidebarItem(label: String, onClick: () -> Unit) {
    Text(
        text = label,
        color = Color.LightGray,
        fontSize = 13.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() }
    )
}
