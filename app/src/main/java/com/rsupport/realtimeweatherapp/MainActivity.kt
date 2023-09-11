package com.rsupport.realtimeweatherapp

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.rsupport.map.presentation.Route
import com.rsupport.map.presentation.viewmodel.WeatherViewModel
import com.rsupport.maputil.convertGRID_GPS
import com.rsupport.realtimeweatherapp.ui.theme.RealTimeWeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: WeatherViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            RealTimeWeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(bottomBar = {
                        BottomNavigation(
                            items = listOf(
                                BottomNavItem(
                                    name = Route.HOME,
                                    route = Route.HOME,
                                    icon = Icons.Default.Home
                                ),
                                BottomNavItem(
                                    name = Route.DETAIL,
                                    route = Route.DETAIL,
                                    icon = Icons.Default.Notifications,
                                ),
                            ),
                            navController = navController,
                            onItemClick = {
                                navController.navigate(it.route) {
                                    popUpTo(it.route)
                                    launchSingleTop = true
                                }
                            })
                    }) {
                        WeatherNavHost(
                            navHostController = navController,
                            weatherViewModel = viewModel
                        )
                    }
                }
            }
        }
        if (checkLocationPermission()) {
            getLastLocation()
        } else {
            requestLocationPermission()
        }
    }

    private fun checkLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
            1000
        )
    }

    private fun getLastLocation() {
        val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        val providers: List<String> = locationManager.getProviders(true)
        var location: Location? = null
        for (provider in providers) {
            val l = locationManager.getLastKnownLocation(provider) ?: continue
            if (location == null || l.accuracy < location.accuracy) {
                // 더 정확한 위치 정보를 사용합니다.
                location = l
            }
        }

        location?.let {
            Log.e("location", "$location")
            fetchMap(location.latitude, location.longitude)
        }
    }

    private fun fetchMap(lat: Double, lng: Double) {
        val currentDate = LocalDate.now()
        val oneHourAgo = LocalTime.now().minusHours(1)
        val formattedTime = oneHourAgo.format(DateTimeFormatter.ofPattern("HHmm"))
        val formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"))
        val xy = convertGRID_GPS(0, lat, lng)
        lifecycleScope.launch {
            viewModel.fetchWeather(
                this@MainActivity,
                1,
                10,
                "JSON",
                formattedDate,
                formattedTime,
                xy.x.toInt(),
                xy.y.toInt(),
                lat,
                lng
            )
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