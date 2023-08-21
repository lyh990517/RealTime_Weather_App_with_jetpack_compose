package com.rsupport.map.presentation.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.MapProperties
import com.naver.maps.map.compose.MapUiSettings
import com.naver.maps.map.compose.Marker
import com.naver.maps.map.compose.MarkerState
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.overlay.OverlayImage
import com.rsupport.core.model.WeatherUiModel
import com.rsupport.map.R
import com.rsupport.map.presentation.state.MapState

@Composable
fun MapScreen(state: State<MapState>){
    when(state.value) {
        is MapState.Loading -> CircularProgressIndicator()
        is MapState.Success -> MapContent((state.value as MapState.Success).data)
        is MapState.Fail -> LoadingFail()
    }
}

@Composable
fun LoadingFail() {
    Text(text = "LoadingFail")
}

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun MapContent(weatherData: WeatherUiModel) {
    val mapProperties = remember {
        mutableStateOf(
            MapProperties(maxZoom = 16.0, minZoom = 15.0)
        )
    }
    val mapUiSettings = remember {
        mutableStateOf(
            MapUiSettings(isLocationButtonEnabled = false)
        )
    }
    Box(Modifier.fillMaxSize()) {
        NaverMap(properties = mapProperties.value, uiSettings = mapUiSettings.value){
            Marker(
                state = MarkerState(position = LatLng(37.532600, 127.024612)),
                captionText = "Marker in Seoul",
                icon = OverlayImage.fromResource(com.rsupport.ui_component.R.drawable.ic_launcher_background)
            )
            Marker(
                state = MarkerState(position = LatLng(37.390791, 127.096306)),
                captionText = "Marker in Pangyo"
            )
        }
        Column {
            Button(onClick = {
                mapProperties.value = mapProperties.value.copy(
                    isBuildingLayerGroupEnabled = !mapProperties.value.isBuildingLayerGroupEnabled
                )
            }) {
                Text(text = "Toggle isBuildingLayerGroupEnabled")
            }
            Button(onClick = {
                mapUiSettings.value = mapUiSettings.value.copy(
                    isLocationButtonEnabled = !mapUiSettings.value.isLocationButtonEnabled
                )
            }) {
                Text(text = "Toggle isLocationButtonEnabled")
            }
        }
    }
}
