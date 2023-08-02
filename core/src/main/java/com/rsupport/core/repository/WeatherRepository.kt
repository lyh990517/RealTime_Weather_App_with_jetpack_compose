package com.rsupport.core.repository

import com.rsupport.core.mapper.WeatherMapper
import com.rsupport.core.model.ModelWrapper
import com.rsupport.core.model.WeatherUiModel
import com.rsupport.core.network.api.IWeatherApi
import com.rsupport.network_contract.entity.WeatherItem
import com.rsupport.network_contract.response.WeatherResponse
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val iWeatherApi: IWeatherApi
) : IWeatherRepository {
    override suspend fun getWeather(
        pageNo: Int,
        numOfRows: Int,
        dataType: String,
        baseDate: String,
        baseTime: String,
        nx: Int,
        ny: Int
    ): ModelWrapper<WeatherUiModel?> {
        return WeatherMapper.mapFromApiResult(
            apiResponse = iWeatherApi.getWeather(
                pageNo,
                numOfRows,
                dataType,
                baseDate,
                baseTime,
                nx,
                ny
            )
        )
    }
}