package com.rsupport.detail.view

import android.graphics.Bitmap
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rsupport.core.model.WeatherUiModel
import com.rsupport.detail.R
import com.rsupport.detail.state.DetailState

@Composable
fun DetailScreen(state: State<DetailState>, navHostController: NavHostController) {
    when (state.value) {
        is DetailState.Success -> {
            val data = state.value as DetailState.Success
            DetailContent(data.weatherUiModel, data.bitmap)
        }

        is DetailState.Uninitialized -> CircularProgressIndicator()
    }
    BackHandler {
        navHostController.popBackStack()
    }
}

@Composable
fun DetailContent(weatherUiModel: WeatherUiModel, bitmap: Bitmap) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Image(bitmap = bitmap.asImageBitmap(), contentDescription = "")
        Text(stringResource(R.string.weather_type, weatherUiModel.weatherType))
        Text(stringResource(R.string.humidity, weatherUiModel.humidity))
        Text(stringResource(R.string.hourly_precipitation, weatherUiModel.hourlyPrecipitation))
        Text(stringResource(R.string.temperature, weatherUiModel.temperature))
        Text(stringResource(R.string.east_west_wind_component, weatherUiModel.eastWestWindComponent))
        Text(stringResource(R.string.wind_direction, weatherUiModel.windDirection))
        Text(stringResource(R.string.north_south_wind_component, weatherUiModel.northSouthWindComponent))
        Text(stringResource(R.string.wind_speed, weatherUiModel.windSpeed))
    }
}

@Preview
@Composable
fun DetailPreview() {
    val weatherUiModel = WeatherUiModel(
        "1", // Weather Type
        "80", // Humidity
        "10", // Hourly Precipitation
        "25.8", // Temperature
        "0", // East-West Wind Component
        "177", // Wind Direction
        "1.9", // North-South Wind Component
        "1.9" // Wind Speed
    )
}
