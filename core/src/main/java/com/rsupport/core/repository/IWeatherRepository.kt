package com.rsupport.core.repository

import com.rsupport.core.model.ModelWrapper
import com.rsupport.core.model.WeatherUiModel
import com.rsupport.network_contract.entity.WeatherItem
import com.rsupport.network_contract.response.WeatherResponse

interface IWeatherRepository {
    suspend fun getWeather(
        pageNo: Int,
        numOfRows: Int,
        dataType: String,
        baseDate: String,
        baseTime: String,
        nx: Int,
        ny: Int
    ): ModelWrapper<WeatherUiModel?>
}