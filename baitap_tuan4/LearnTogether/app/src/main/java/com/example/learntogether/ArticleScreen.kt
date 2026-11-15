package com.example.learntogether

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learntogether.ui.theme.LearnTogetherTheme

@Composable
fun ArticleScreen() {
    // Hàm này tập hợp các dữ liệu và gọi hàm hiển thị chi tiết
    ArticleCard(
        title = "Jetpack Compose tutorial",
        shortDescription = "Jetpack Compose is a modern toolkit for building native Android UI. Compose simplifies and accelerates UI development on Android with less code, powerful tools, and intuitive Kotlin APIs.",
        longDescription = "In this tutorial, you build a simple UI component with composable functions. You learn how to use composable functions to define your UI, and how to use the Compose Compiler to transform these functions into UI elements. You also learn how to use modifiers to customize your UI components.",
        imagePainter = painterResource(R.drawable.bg_compose_background) // Sử dụng ảnh bạn đã import
    )
}

@Composable
private fun ArticleCard(
    title: String,
    shortDescription: String,
    longDescription: String,
    imagePainter: Painter,
    modifier: Modifier = Modifier
) {
    // Column sắp xếp ảnh và chữ theo chiều dọc
    Column(modifier = modifier) {
        Image(
            painter = imagePainter,
            contentDescription = null
        )
        Text(
            text = title,
            modifier = Modifier.padding(16.dp),
            fontSize = 24.sp
        )
        Text(
            text = shortDescription,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            textAlign = TextAlign.Justify // Căn đều 2 bên văn bản
        )
        Text(
            text = longDescription,
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Justify
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ArticlePreview() {
    LearnTogetherTheme {
        ArticleScreen()
    }
}