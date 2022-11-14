package com.example.hellofigma

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hellofigma.pages.AddDevicePage
import com.example.hellofigma.pages.DevicePage
import com.example.hellofigma.pages.ListPage

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "ListPage"
    ) {
        composable("ListPage") { ListPage(
            onNavigateToDevicePage = { navController.navigate("DevicePage")},
            onNavigateToAddDevicePage = {
                Log.i("siema","siema")
                navController.navigate("AddDevicePage")}

        ) }
        composable("DevicePage") { DevicePage() }
        composable("AddDevicePage"){ AddDevicePage()}

    }

}





