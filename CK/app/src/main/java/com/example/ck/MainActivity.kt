package com.example.ck

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ck.ui.theme.CKTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CKTheme {
                SBikeApp()
            }
        }
    }
}

@Composable
fun SBikeApp() {
    val navController = rememberNavController()

    // Bọc NavHost trong MainContainer để hiển thị Sidebar và TopBar chung
    MainContainer(navController = navController) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            NavHost(navController = navController, startDestination = "login") {
                composable("login") {
                    LoginScreen(navController)
                }
                composable("dashboard") {
                    DashboardScreen(navController)
                }
                composable("profile") {
                    ProfileScreen(navController)
                }
                composable("store_profile") {
                    StoreProfileScreen(navController)
                }
                composable(
                    route = "bike_management/{filter}",
                    arguments = listOf(navArgument("filter") { type = NavType.StringType })
                ) { backStackEntry ->
                    val filter = backStackEntry.arguments?.getString("filter") ?: "all"
                    BikeManagementScreen(navController, filter)
                }
                composable(
                    route = "contract_management/{filter}",
                    arguments = listOf(navArgument("filter") { type = NavType.StringType })
                ) { backStackEntry ->
                    val filter = backStackEntry.arguments?.getString("filter") ?: "summary"
                    ContractManagementScreen(navController, filter)
                }
                composable(
                    route = "revenue_management/{type}",
                    arguments = listOf(navArgument("type") { type = NavType.StringType })
                ) { backStackEntry ->
                    val type = backStackEntry.arguments?.getString("type") ?: "daily"
                    RevenueScreen(navController, type)
                }
            }
        }
    }
}
