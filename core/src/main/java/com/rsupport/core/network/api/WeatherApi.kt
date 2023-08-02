package com.rsupport.core.network.api

import com.rsupport.network_contract.IWeatherDataRequester
import com.rsupport.network_contract.response.ApiResponse
import com.rsupport.network_contract.response.WeatherResponse
import javax.inject.Inject

class WeatherApi @Inject constructor(
    private val iWeatherDataRequester: IWeatherDataRequester
) : IWeatherApi {
    override suspend fun getWeather(
        pageNo: Int,
        numOfRows: Int,
        dataType: String,
        baseDate: String,
        baseTime: String,
        nx: Int,
        ny: Int
    ): ApiResponse<WeatherResponse?> {
        return iWeatherDataRequester.request(
            pageNo,
            numOfRows,
            dataType,
            baseDate,
            baseTime,
            nx,
            ny
        )
    }

}