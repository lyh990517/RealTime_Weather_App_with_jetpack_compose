package com.rsupport.core.mapper

import com.rsupport.core.model.ModelWrapper
import com.rsupport.core.model.WeatherModel
import com.rsupport.core.model.WeatherUiModel
import com.rsupport.network_contract.entity.WeatherItem
import com.rsupport.network_contract.response.ApiResponse
import com.rsupport.network_contract.response.WeatherResponse


object WeatherMapper {
    fun mapFromApiResult(apiResponse: ApiResponse<WeatherResponse?>): ModelWrapper<WeatherUiModel?> {
        val result = when (apiResponse) {
            is ApiResponse.Success -> {
                ModelWrapper.Success(apiResponse.data?.response?.body?.items?.item?.let { toWeatherUiModel(it) })
            }

            is ApiResponse.Fail -> {
                ModelWrapper.Fail(apiResponse.error)
            }
        }
        return result
    }

    private fun toWeatherModel(weatherItem: WeatherItem) = WeatherModel(
        baseDate = weatherItem.baseDate,
        baseTime = weatherItem.baseTime,
        category = weatherItem.category,
        nx = weatherItem.nx,
        ny = weatherItem.ny,
        obsrValue = weatherItem.obsrValue
    )

    fun toWeatherUiModel(weatherItem: List<WeatherItem>) =
        WeatherUiModel(weatherItem.map { weather -> toWeatherModel(weather) })
}