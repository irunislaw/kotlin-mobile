package com.example.hellofigma

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hellofigma.pages.AddDevicePage
import com.example.hellofigma.pages.DevicePage
import com.example.hellofigma.pages.ListPage
import com.example.hellofigma.validation.MainState
import com.example.hellofigma.validation.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "ListPage"
    ) {
        composable("ListPage") {
            ListPage(
                onNavigateToDevicePage = { navController.navigate("DevicePage")},
                onNavigateToAddDevicePage = {
                    Log.i("siema","siema")
                    navController.navigate("AddDevicePage")}

            )
        }
        composable("DevicePage") { DevicePage() }
        composable("AddDevicePage"){ AddDevicePage(
            onNavigateToListPage = {
                navController.navigate("ListPage")
            },


        )}

    }

}







