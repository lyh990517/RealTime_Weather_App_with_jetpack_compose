package com.rsupport.detail.view

import android.graphics.Bitmap
import androidx.activity.compose.BackHandler
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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

    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id =
                if (weatherUiModel.weatherType == "0")
                    com.rsupport.ui_component.R.drawable.sun_back
                else
                    com.rsupport.ui_component.R.drawable.rain_back
                ),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .height(200.dp), bitmap = bitmap.asImageBitmap(), contentDescription = ""
            )
        }
        Column(modifier = Modifier
            .weight(2f)
            .padding(16.dp)) {
            WeatherInfoItem(R.string.humidity, weatherUiModel.humidity)
            WeatherInfoItem(R.string.hourly_precipitation, weatherUiModel.hourlyPrecipitation)
            WeatherInfoItem(R.string.temperature, weatherUiModel.temperature)
            WeatherInfoItem(R.string.east_west_wind_component, weatherUiModel.eastWestWindComponent)
            WeatherInfoItem(R.string.wind_direction, weatherUiModel.windDirection)
            WeatherInfoItem(R.string.north_south_wind_component, weatherUiModel.northSouthWindComponent)
            WeatherInfoItem(R.string.wind_speed, weatherUiModel.windSpeed)
        }
    }
}

@Composable
fun WeatherInfoItem(@StringRes string: Int, text: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        shape = RoundedCornerShape(6.dp),
        colors = CardDefaults.cardColors(Color.LightGray)
    ) {
        Text(modifier = Modifier.padding(5.dp), text = stringResource(string, text))
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
