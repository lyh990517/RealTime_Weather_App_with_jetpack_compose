package com.rsupport.map.presentation.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.rsupport.map.presentation.state.MapState

@Composable
fun MapScreen(state: State<MapState>){
    Text(text = "${state.value}")
}