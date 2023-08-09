package com.rsupport.realtimeweatherapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rsupport.map.presentation.MapScreen
import com.rsupport.map.presentation.viewmodel.WeatherViewModel

@Composable
fun WeatherNavHost(
    navHostController: NavHostController = rememberNavController(),
    weatherViewModel: WeatherViewModel
) {
    NavHost(navController = navHostController, startDestination = "home") {
        composable("home") {
            MapScreen(weatherViewModel.uiState.collectAsState())
        }
    }
}