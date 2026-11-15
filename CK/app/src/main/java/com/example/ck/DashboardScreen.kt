package com.example.ck

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun DashboardScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .verticalScroll(rememberScrollState())
    ) {
        SummarySection()
        ChartSection()
    }
}

@Composable
fun SummarySection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SummaryCard("Khách hàng", "4", Color.Blue, Modifier.weight(1f))
        SummaryCard("Lượt thuê", "0", Color.Gray, Modifier.weight(1f))
        SummaryCard("Tổng xe", "5", Color(0xFF009688), Modifier.weight(1f))
        SummaryCard("Đang sẵn sàng", "4", Color(0xFF009688), Modifier.weight(1f))
        SummaryCard("Doanh thu", "0 đ", Color.Black, Modifier.weight(1f))
    }
}

@Composable
fun SummaryCard(label: String, value: String, valueColor: Color, modifier: Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(label, fontSize = 10.sp, color = Color.Gray, maxLines = 1)
            Text(value, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = valueColor, maxLines = 1)
        }
    }
}

@Composable
fun ChartSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Biểu đồ thống kê tình hình sử dụng", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(24.dp))
            
            Box(modifier = Modifier.fillMaxWidth().height(200.dp)) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val path = Path().apply {
                        moveTo(0f, size.height * 0.7f)
                        cubicTo(
                            size.width * 0.2f, size.height * 0.2f,
                            size.width * 0.5f, size.height * 0.9f,
                            size.width, size.height * 0.4f
                        )
                    }
                    drawPath(
                        path = path,
                        color = Color(0xFF009688).copy(alpha = 0.5f),
                        style = Stroke(width = 4f)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Jan 01", fontSize = 12.sp, color = Color.Gray)
                Text("Jan 03", fontSize = 12.sp, color = Color.Gray)
                Text("Jan 05", fontSize = 12.sp, color = Color.Gray)
            }
        }
    }
}
