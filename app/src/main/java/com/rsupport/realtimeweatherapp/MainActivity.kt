package com.rsupport.realtimeweatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.rsupport.map.presentation.viewmodel.WeatherViewModel
import com.rsupport.realtimeweatherapp.ui.theme.RealTimeWeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

   private val viewModel : WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchMap()
        setContent {
            RealTimeWeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WeatherNavHost(weatherViewModel = viewModel)
                }
            }
        }
    }

    private fun fetchMap() {
        val currentDate = LocalDate.now()
        val oneHourAgo = LocalTime.now().minusHours(1)
        val formattedTime = oneHourAgo.format(DateTimeFormatter.ofPattern("HHmm"))
        val formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"))
        lifecycleScope.launch {
            viewModel.fetchWeather(1, 10, "JSON", formattedDate, formattedTime, 55, 127)
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RealTimeWeatherAppTheme {
        Greeting("Android")
    }
}