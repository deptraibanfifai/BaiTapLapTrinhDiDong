package com.example.ck

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.*
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
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContainer(navController: NavController, content: @Composable (PaddingValues) -> Unit) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var showUserMenu by remember { mutableStateOf(false) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: ""

    // Không hiển thị Sidebar/TopBar ở màn hình login
    if (currentRoute == "login") {
        content(PaddingValues(0.dp))
        return
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.width(280.dp),
                drawerContainerColor = Color(0xFF2C3E50)
            ) {
                SidebarContent(navController) {
                    scope.launch { drawerState.close() }
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                // Chỉ hiện TopAppBar chung cho các màn hình chính
                if (currentRoute == "dashboard" || currentRoute == "profile" || currentRoute == "store_profile") {
                    TopAppBar(
                        title = { Text("S-BIKE", fontWeight = FontWeight.Bold, color = Color.White) },
                        navigationIcon = {
                            IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color.White)
                            }
                        },
                        actions = {
                            IconButton(onClick = { }) {
                                Icon(Icons.Default.Email, contentDescription = null, tint = Color.White)
                            }
                            Box {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(horizontal = 8.dp).clickable { showUserMenu = true }
                                ) {
                                    Box(modifier = Modifier.size(32.dp).clip(CircleShape).background(Color.LightGray)) {
                                        Icon(Icons.Default.Person, null, modifier = Modifier.fillMaxSize())
                                    }
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text("Quản trị", color = Color.White, fontSize = 14.sp)
                                    Icon(Icons.Default.ArrowDropDown, null, tint = Color.White)
                                }
                                DropdownMenu(expanded = showUserMenu, onDismissRequest = { showUserMenu = false }) {
                                    DropdownMenuItem(
                                        text = { Text("Hồ sơ") },
                                        onClick = { showUserMenu = false; navController.navigate("profile") }
                                    )
                                    DropdownMenuItem(
                                        text = { Text("Đăng xuất") },
                                        onClick = {
                                            showUserMenu = false
                                            navController.navigate("login") { popUpTo("dashboard") { inclusive = true } }
                                        }
                                    )
                                }
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF009688))
                    )
                }
            }
        ) { padding ->
            content(padding)
        }
    }
}
