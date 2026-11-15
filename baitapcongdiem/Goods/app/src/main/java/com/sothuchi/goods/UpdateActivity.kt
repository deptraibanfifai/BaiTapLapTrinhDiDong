package com.sothuchi.goods

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

class UpdateActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id    = intent.getIntExtra("id", 0)
        val name  = intent.getStringExtra("name") ?: ""
        val desc  = intent.getStringExtra("desc") ?: ""
        val price = intent.getDoubleExtra("price", 0.0)
        setContent { UpdateProductScreen(id, name, desc, price) }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateProductScreen(id: Int, initName: String, initDesc: String, initPrice: Double) {
    val context = LocalContext.current
    val db = remember { DatabaseHelper(context) }
    var name  by remember { mutableStateOf(initName) }
    var desc  by remember { mutableStateOf(initDesc) }
    var price by remember { mutableStateOf(initPrice.toString()) }
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Xác nhận xoá") },
            text  = { Text("Bạn có chắc muốn xoá sản phẩm này?") },
            confirmButton = {
                TextButton(onClick = {
                    db.deleteProduct(id)
                    (context as ComponentActivity).finish()
                }) { Text("Xoá") }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) { Text("Huỷ") }
            }
        )
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Cập nhật sản phẩm") }) }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(value = name, onValueChange = { name = it },
                label = { Text("Tên sản phẩm") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = desc, onValueChange = { desc = it },
                label = { Text("Mô tả") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = price, onValueChange = { price = it },
                label = { Text("Giá") }, modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))

            Button(
                onClick = {
                    db.updateProduct(Product(id, name, desc, price.toDoubleOrNull() ?: 0.0))
                    (context as ComponentActivity).finish()
                },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Cập nhật") }

            OutlinedButton(
                onClick = { showDialog = true },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.error)
            ) { Text("Xoá sản phẩm") }
        }
    }
}