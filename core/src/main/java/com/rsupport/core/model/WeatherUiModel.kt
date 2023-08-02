package com.rsupport.core.model

import com.rsupport.network_contract.entity.Response
import com.rsupport.network_contract.entity.WeatherItem

data class WeatherUiModel(
    val weathers: List<WeatherModel>
)