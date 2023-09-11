package com.rsupport.core.local

import com.rsupport.core.model.WeatherUiModel
import kotlinx.coroutines.flow.MutableStateFlow

object WeatherUiModelSingleton {
    private var weatherUiModel = MutableStateFlow<WeatherUiModel?>(null)

    fun setWeatherUiModel(model: WeatherUiModel) {
        weatherUiModel.value = model
    }

    fun getWeatherUiModel(): WeatherUiModel? {
        return weatherUiModel.value
    }
}