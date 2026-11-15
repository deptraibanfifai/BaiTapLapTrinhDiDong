package com.example.businesscardscreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscardscreen.ui.theme.BusinessCardScreenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardScreenTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFD2E8D4)
                ) {
                    BusinessCardScreenContent()
                }
            }
        }
    }
}

@Composable
fun BusinessCardScreenContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            val image = painterResource(id = R.drawable.android_logo)
            Box(
                modifier = Modifier
                    .background(Color(0xFF073042))
                    .padding(8.dp)
            ) {
                Image(
                    painter = image,
                    contentDescription = null,
                    modifier = Modifier.width(120.dp)
                )
            }
            Text(
                text = "Hieu Nguyen",
                fontSize = 45.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(top = 12.dp)
            )
            Text(
                text = "Android Developer Extraordinaire",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF006D3B)
            )
        }

        Spacer(modifier = Modifier.weight(1f))


        Column(modifier = Modifier.padding(bottom = 48.dp)) {
            ContactInfoItem(icon = Icons.Default.Phone, text = " 0898407761")
            ContactInfoItem(icon = Icons.Default.Share, text = "@min.hiwe")
            ContactInfoItem(icon = Icons.Default.Email, text = "hieunm.24it@vku.udn.vn")
        }
    }
}

@Composable
fun ContactInfoItem(icon: ImageVector, text: String) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .width(250.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFF006D3B),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(text = text, fontSize = 16.sp, color = Color(0xFF073042))
    }
}

@Preview(showBackground = true)
@Composable
fun BusinessCardScreenPreview() {
    BusinessCardScreenTheme {
        BusinessCardScreenContent()
    }
}