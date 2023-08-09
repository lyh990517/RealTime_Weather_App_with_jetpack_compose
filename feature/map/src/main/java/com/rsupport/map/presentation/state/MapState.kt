package com.rsupport.map.presentation.state

import com.rsupport.core.model.WeatherUiModel

sealed class MapState {
    object Loading : MapState()
    data class Success(val data : WeatherUiModel) : MapState()

    data class Fail(val throwable: Throwable) : MapState()
}
