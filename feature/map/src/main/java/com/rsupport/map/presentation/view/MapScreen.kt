package com.rsupport.map.presentation.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.MapProperties
import com.naver.maps.map.compose.MapUiSettings
import com.naver.maps.map.compose.NaverMap
import com.rsupport.map.presentation.state.MapState

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun MapScreen(state: State<MapState>){
    val mapProperties = remember {
        mutableStateOf(
            MapProperties(maxZoom = 10.0, minZoom = 5.0)
        )
    }
    val mapUiSettings = remember {
        mutableStateOf(
            MapUiSettings(isLocationButtonEnabled = false)
        )
    }
    Box(Modifier.fillMaxSize()) {
        NaverMap(properties = mapProperties.value, uiSettings = mapUiSettings.value)
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