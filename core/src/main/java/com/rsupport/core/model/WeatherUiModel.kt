package com.rsupport.core.model

data class WeatherUiModel(
    val weatherType: String, // 강수 형태 코드 ("1" - 비)
    val humidity: String, // 습도 ("80" - 80%)
    val hourlyPrecipitation: String, // 1시간 강수량 ("10" - 10mm)
    val temperature: String, // 기온 ("25.8" - 25.8°C)
    val eastWestWindComponent: String, // 동서바람성분 ("0" - 0 m/s)
    val windDirection: String, // 풍향 ("177" - 177°)
    val northSouthWindComponent: String, // 남북바람성분 ("1.9" - 1.9 m/s)
    val windSpeed: String  // 풍속 ("1.9" - 1.9 m/s)
)