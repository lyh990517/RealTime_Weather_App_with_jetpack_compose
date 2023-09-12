package com.rsupport.realtimeweatherapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rsupport.detail.state.DetailState
import com.rsupport.detail.view.DetailScreen
import com.rsupport.detail.viewmodel.DetailViewModel
import com.rsupport.map.presentation.Route
import com.rsupport.map.presentation.view.MapScreen
import com.rsupport.map.presentation.viewmodel.WeatherViewModel

@Composable
fun WeatherNavHost(
    navHostController: NavHostController = rememberNavController(),
    weatherViewModel: WeatherViewModel,
    detailViewModel: DetailViewModel
) {

    NavHost(navController = navHostController, startDestination = Route.MAP) {
        composable(Route.MAP) {
            MapScreen(
                weatherViewModel.uiState.collectAsState(),
                navHostController
            ) { model, bitmap ->
                detailViewModel.uiState.value = DetailState.Success(model, bitmap)
            }
        }
        composable(Route.DETAIL) {
            DetailScreen(detailViewModel.uiState.collectAsState(),navHostController = navHostController)
        }
    }
}