package com.rsupport.network_contract

import com.rsupport.network_contract.response.ApiResponse
import com.rsupport.network_contract.response.WeatherResponse

interface IWeatherDataRequester {
    suspend fun request(
        pageNo: Int,
        numOfRows: Int,
        dataType: String,
        baseDate: String,
        baseTime: String,
        nx: Int,
        ny: Int
    ): ApiResponse<WeatherResponse?>
}