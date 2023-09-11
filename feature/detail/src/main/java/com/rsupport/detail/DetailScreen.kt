package com.rsupport.detail

import androidx.activity.compose.BackHandler
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.rsupport.core.local.WeatherUiModelSingleton

@Composable
fun DetailScreen(navHostController: NavHostController){
   when(WeatherUiModelSingleton.getWeatherUiModel()){
       null -> CircularProgressIndicator()
       else -> {
           Text(text = "${WeatherUiModelSingleton.getWeatherUiModel()}")
       }
   }
    BackHandler {
        navHostController.popBackStack()
    }
}