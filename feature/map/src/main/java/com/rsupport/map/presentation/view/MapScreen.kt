package com.rsupport.map.presentation.view

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.compose.CameraPositionState
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.MapProperties
import com.naver.maps.map.compose.MapUiSettings
import com.naver.maps.map.compose.Marker
import com.naver.maps.map.compose.MarkerState
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.rememberCameraPositionState
import com.naver.maps.map.overlay.OverlayImage
import com.rsupport.core.model.WeatherUiModel
import com.rsupport.map.R
import com.rsupport.map.presentation.state.MapState
import com.rsupport.maputil.convertGRID_GPS

@Composable
fun MapScreen(state: State<MapState>){
    when(state.value) {
        is MapState.Loading -> CircularProgressIndicator()
        is MapState.Success -> MapContent((state.value as MapState.Success))
        is MapState.Fail -> LoadingFail()
    }
}

@Composable
fun LoadingFail() {
    Text(text = "LoadingFail")
}

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun MapContent(weatherData: MapState.Success) {
    val mapState by remember { mutableStateOf(weatherData) }
    val current = LatLng(mapState.lat, mapState.lng)
    val mapProperties = remember {
        mutableStateOf(
            MapProperties(maxZoom = 16.0, minZoom = 5.0)
        )
    }
    val mapUiSettings = remember {
        mutableStateOf(
            MapUiSettings(isLocationButtonEnabled = false)
        )
    }
    val cameraPositionState: CameraPositionState = rememberCameraPositionState {
        position = CameraPosition(current, 11.0)
    }
    LaunchedEffect(Unit){
        cameraPositionState.move(CameraUpdate.zoomIn())
    }
    Box(Modifier.fillMaxSize()) {
        NaverMap(properties = mapProperties.value, uiSettings = mapUiSettings.value, cameraPositionState = cameraPositionState){
            Marker(
                state = MarkerState(position = current),
                captionText = "현재 위치",
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
