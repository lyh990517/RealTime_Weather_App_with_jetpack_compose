package com.rsupport.map.presentation.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.compose.CameraPositionState
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.MapProperties
import com.naver.maps.map.compose.Marker
import com.naver.maps.map.compose.MarkerState
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.rememberCameraPositionState
import com.naver.maps.map.overlay.OverlayImage
import com.rsupport.core.mapper.ResourceMapper
import com.rsupport.map.presentation.state.MapState

@Composable
fun MapScreen(state: State<MapState>) {
    when (state.value) {
        is MapState.Loading -> LoadingContent()
        is MapState.Success -> MapContent((state.value as MapState.Success))
        is MapState.Fail -> LoadingFail()
    }
}

@Composable
private fun LoadingContent() {
    Box {
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize()
        )
    }
}

@Composable
fun LoadingFail() {
    Text(text = "LoadingFail")
}

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun MapContent(weatherData: MapState.Success) {
    val currentPosition = LatLng(weatherData.lat, weatherData.lng)
    val cameraPositionState: CameraPositionState = rememberCameraPositionState {
        position = CameraPosition(currentPosition, 11.0)
    }
    val weatherResource = ResourceMapper.getWeatherResource(
        weatherData.data.weathers[0].obsrValue,
        LocalContext.current
    )
    LaunchedEffect(Unit) {
        cameraPositionState.move(CameraUpdate.zoomIn())
    }
    Box(Modifier.fillMaxSize()) {
        NaverMap(
            properties = MapProperties(maxZoom = 16.0, minZoom = 5.0),
            cameraPositionState = cameraPositionState,
        ) {
            Marker(
                state = MarkerState(position = currentPosition),
                captionText = "현재 위치",
                icon = OverlayImage.fromBitmap(weatherResource)
            ) { marker ->
                cameraPositionState.move(CameraUpdate.zoomIn())
                true
            }
        }
    }
}

