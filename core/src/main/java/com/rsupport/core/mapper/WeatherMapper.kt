package com.rsupport.core.mapper

import com.rsupport.core.model.ModelWrapper
import com.rsupport.core.model.WeatherUiModel
import com.rsupport.network_contract.entity.WeatherItem
import com.rsupport.network_contract.response.ApiResponse
import com.rsupport.network_contract.response.WeatherResponse


object WeatherMapper {
    fun mapFromApiResult(apiResponse: ApiResponse<WeatherResponse?>): ModelWrapper<WeatherUiModel?> {
        val result = when (apiResponse) {
            is ApiResponse.Success -> {
                ModelWrapper.Success(apiResponse.data?.response?.body?.items?.item?.let {
                    toWeatherUiModel(
                        it
                    )
                })
            }

            is ApiResponse.Fail -> {
                ModelWrapper.Fail(apiResponse.error)
            }
        }
        return result
    }

    private fun toWeatherUiModel(weatherItems: List<WeatherItem>): WeatherUiModel {
        val weatherTypeItem = weatherItems.firstOrNull { it.category == "PTY" }
        val humidityItem = weatherItems.firstOrNull { it.category == "REH" }
        val hourlyPrecipitationItem = weatherItems.firstOrNull { it.category == "RN1" }
        val temperatureItem = weatherItems.firstOrNull { it.category == "T1H" }
        val eastWestWindComponentItem = weatherItems.firstOrNull { it.category == "UUU" }
        val windDirectionItem = weatherItems.firstOrNull { it.category == "VEC" }
        val northSouthWindComponentItem = weatherItems.firstOrNull { it.category == "VVV" }
        val windSpeedItem = weatherItems.firstOrNull { it.category == "WSD" }

        return WeatherUiModel(
            weatherType = weatherTypeItem?.obsrValue ?: "",
            humidity = humidityItem?.obsrValue ?: "",
            hourlyPrecipitation = hourlyPrecipitationItem?.obsrValue ?: "",
            temperature = temperatureItem?.obsrValue ?: "",
            eastWestWindComponent = eastWestWindComponentItem?.obsrValue ?: "",
            windDirection = windDirectionItem?.obsrValue ?: "",
            northSouthWindComponent = northSouthWindComponentItem?.obsrValue ?: "",
            windSpeed = windSpeedItem?.obsrValue ?: ""
        )
    }
}