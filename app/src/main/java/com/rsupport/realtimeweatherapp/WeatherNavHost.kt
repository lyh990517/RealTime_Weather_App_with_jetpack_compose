package com.rsupport.realtimeweatherapp

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rsupport.detail.DetailScreen
import com.rsupport.map.presentation.Route
import com.rsupport.map.presentation.view.MapScreen
import com.rsupport.map.presentation.viewmodel.WeatherViewModel

@Composable
fun WeatherNavHost(
    navHostController: NavHostController = rememberNavController(),
    weatherViewModel: WeatherViewModel
) {

    NavHost(navController = navHostController, startDestination = Route.HOME) {
        composable(Route.HOME) {
            MapScreen(weatherViewModel.uiState.collectAsState(),navHostController)
        }
        composable(Route.DETAIL){
            DetailScreen()
        }
    }
}