package com.rsupport.core.model

import com.rsupport.network_contract.entity.WeatherItem

data class WeatherModel(
    val baseDate: String,
    val baseTime: String,
    val category: String,
    val nx: Int,
    val ny: Int,
    val obsrValue: String
)