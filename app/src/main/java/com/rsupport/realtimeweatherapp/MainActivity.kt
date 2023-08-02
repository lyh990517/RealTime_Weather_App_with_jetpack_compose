package com.rsupport.realtimeweatherapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.rsupport.network_contract.response.ApiResponse
import com.rsupport.network_contract.IWeatherDataRequester
import com.rsupport.realtimeweatherapp.ui.theme.RealTimeWeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var weatherRequester: IWeatherDataRequester

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            val result = weatherRequester.request(1, 10, "JSON", "20230802", "0600", 55, 127)
            when(result){
                is ApiResponse.Success -> {
                    val response = result.data?.response
                    response?.body?.items?.item?.forEach { weatherItem ->
                        Log.e("weather","$weatherItem")
                    }
                }
                is ApiResponse.Fail -> {
                    Log.e("weather","fail")
                }
            }
        }
        setContent {
            RealTimeWeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
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