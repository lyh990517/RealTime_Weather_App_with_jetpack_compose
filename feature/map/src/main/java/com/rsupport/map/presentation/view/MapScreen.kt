package com.rsupport.map.presentation.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
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
import com.rsupport.map.presentation.Route
import com.rsupport.map.presentation.state.MapState

@Composable
fun MapScreen(state: State<MapState>,navHostController: NavHostController) {
    val weatherMarkerState = remember { mutableStateOf<MapState.Success?>(null) }
    when (state.value) {
        is MapState.Loading -> LoadingContent()
        is MapState.Success -> {
            weatherMarkerState.value = state.value as MapState.Success
            MapContent(weatherMarkerState,navHostController)
        }
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
fun MapContent(weatherMarkerState: State<MapState.Success?>,navHostController: NavHostController) {
    val currentPosition = LatLng(weatherMarkerState.value?.lat ?: 0.0, weatherMarkerState.value?.lng ?: 0.0)
    val cameraPositionState: CameraPositionState = rememberCameraPositionState {
        position = CameraPosition(currentPosition, 16.0)
    }
    LaunchedEffect(Unit) {
        cameraPositionState.move(CameraUpdate.zoomIn())
    }
    Box(Modifier.fillMaxSize()) {
        NaverMap(
            properties = MapProperties(maxZoom = 16.0, minZoom = 12.0),
            cameraPositionState = cameraPositionState,
        ) {
            Marker(
                state = MarkerState(position = currentPosition),
                captionText = "현재 위치",
                icon = OverlayImage.fromBitmap(weatherMarkerState.value?.weatherImage!!)
            ){
                navHostController.navigate(Route.DETAIL)
                true
            }
        }
    }
}
