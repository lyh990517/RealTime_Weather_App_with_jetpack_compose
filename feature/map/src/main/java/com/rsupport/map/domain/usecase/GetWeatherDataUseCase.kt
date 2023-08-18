package com.rsupport.map.domain.usecase

import com.rsupport.core.repository.IWeatherRepository
import javax.inject.Inject

class GetWeatherDataUseCase @Inject constructor(private val repository: IWeatherRepository) {
    suspend operator fun invoke(
        pageNo: Int,
        numOfRows: Int,
        dataType: String,
        baseDate: String,
        baseTime: String,
        nx: Int,
        ny: Int
    ) = repository.getWeather(pageNo, numOfRows, dataType, baseDate, baseTime, nx, ny)
}