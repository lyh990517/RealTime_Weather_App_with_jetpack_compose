package com.rsupport.core.network.api

import com.rsupport.core.model.ModelWrapper
import com.rsupport.network_contract.entity.Response
import com.rsupport.network_contract.response.ApiResponse
import com.rsupport.network_contract.response.WeatherResponse
import kotlinx.coroutines.flow.Flow

interface IWeatherApi {
    suspend fun getWeather(
        pageNo: Int,
        numOfRows: Int,
        dataType: String,
        baseDate: String,
        baseTime: String,
        nx: Int,
        ny: Int
    ): ApiResponse<WeatherResponse?>
}