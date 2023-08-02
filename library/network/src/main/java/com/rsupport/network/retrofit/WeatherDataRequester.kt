package com.rsupport.network.retrofit

import com.rsupport.network.api.WeatherService
import com.rsupport.network_contract.response.ApiResponse
import com.rsupport.network_contract.IWeatherDataRequester
import com.rsupport.network_contract.response.WeatherResponse
import javax.inject.Inject

class WeatherDataRequester @Inject constructor(
    private val weatherService: WeatherService
) : IWeatherDataRequester {
    override suspend fun request(
        pageNo: Int,
        numOfRows: Int,
        dataType: String,
        baseDate: String,
        baseTime: String,
        nx: Int,
        ny: Int
    ): ApiResponse<WeatherResponse?> {
        val response = weatherService.getWeather(
            serviceKey = "IzXw1F88L1rL6JJuOYmZ+8lCgnUTMhLSAjPaeS6yf2R58uvo1Bki9dBhl/y9VzA97RRD4ff4XTgLfuCtB7jwFw==",
            pageNo,
            numOfRows,
            dataType,
            baseDate,
            baseTime,
            nx,
            ny
        )
        val apiResponse = if (response.isSuccessful) {
            ApiResponse.Success(response.body())
        } else {
            ApiResponse.Fail(Throwable("Error!"))
        }
        return apiResponse
    }
}