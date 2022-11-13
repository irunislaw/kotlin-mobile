package com.example.hellofigma

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "ListPage"
    ) {
        composable("ListPage") { ListPage(onNavigateToDevicePage = { navController.navigate("DevicePage")},) }
        composable("DevicePage") { DevicePage() }

    }

}





