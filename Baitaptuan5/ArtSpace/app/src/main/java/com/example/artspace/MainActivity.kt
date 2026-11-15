package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme
import androidx.compose.foundation.border
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceScreen()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceScreen() {
    // Trạng thái để biết đang hiển thị bức tranh số mấy
    var currentArtwork by remember { mutableStateOf(1) }

    // Dữ liệu giả lập cho các bức tranh
    val imageResource = when (currentArtwork) {
        1 -> R.drawable.artwork_1
        2 -> R.drawable.artwork_2
        else -> R.drawable.artwork_3
    }

    val title = when (currentArtwork) {
        1 -> "Text1 "
        2 -> "Text2"
        else -> "Text++"
    }

    val artist = when (currentArtwork) {
        1 -> "Text3"
        2 -> "Text4"
        else -> "Text++"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // --- PHẦN 1: KHUNG TRANH ---
        Surface(
            // Trong Material 3, Surface sử dụng shadowElevation thay vì elevation
            shadowElevation = 8.dp,
            modifier = Modifier
                .border(width = 2.dp, color = Color.Gray)
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = title,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Fit
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // --- PHẦN 2: THÔNG TIN CHI TIẾT ---
        Card(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFECEFF1))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = title, fontSize = 24.sp, fontWeight = FontWeight.Light)
                Text(text = artist, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // --- PHẦN 3: NÚT ĐIỀU KHIỂN ---
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    if (currentArtwork > 1) currentArtwork--
                    else currentArtwork = 3 // Quay lại ảnh cuối
                },
                modifier = Modifier.width(120.dp)
            ) {
                Text("Previous")
            }

            Button(
                onClick = {
                    if (currentArtwork < 3) currentArtwork++
                    else currentArtwork = 1 // Quay lại ảnh đầu
                },
                modifier = Modifier.width(120.dp)
            ) {
                Text("Next")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpaceScreen()
    }
}