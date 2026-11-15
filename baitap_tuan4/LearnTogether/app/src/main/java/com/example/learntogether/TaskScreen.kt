package com.example.learntogether

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learntogether.ui.theme.LearnTogetherTheme

@Composable
fun TaskScreen() {
    // Gọi hàm hiển thị với các tham số cụ thể của bài tập Task Manager
    TaskCompletedContent(
        title = "All tasks completed",
        status = "Nice work!",
        imagePainter = painterResource(R.drawable.ic_task_completed) // Đảm bảo file này có trong drawable
    )
}

@Composable
private fun TaskCompletedContent(
    title: String,
    status: String,
    imagePainter: androidx.compose.ui.graphics.painter.Painter,
    modifier: Modifier = Modifier
) {
    // Column bao phủ toàn bộ màn hình để có thể căn giữa nội dung
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center, // Căn giữa theo chiều dọc
        horizontalAlignment = Alignment.CenterHorizontally // Căn giữa theo chiều ngang
    ) {
        Image(
            painter = imagePainter,
            contentDescription = null
        )
        Text(
            text = title,
            fontWeight = FontWeight.Bold, // Chữ in đậm
            modifier = Modifier.padding(top = 24.dp, bottom = 8.dp) // Khoảng cách theo yêu cầu
        )
        Text(
            text = status,
            fontSize = 16.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TaskPreview() {
    LearnTogetherTheme {
        TaskScreen()
    }
}