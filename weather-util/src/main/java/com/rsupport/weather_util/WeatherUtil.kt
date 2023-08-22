package com.rsupport.weather_util

enum class WeatherType {
    SUN,
    RAIN,
    SLEET,
    SNOW,
    SHOWERS,
    DRIZZLE,
    FREEZING_RAIN,
    SNOWFALL
}

fun convert(type: WeatherType): String{
    val weatherType = when (type) {
        WeatherType.SUN -> "0"
        WeatherType.RAIN -> "1"
        WeatherType.SLEET -> "2"
        WeatherType.SNOW -> "3"
        WeatherType.SHOWERS -> "4"
        WeatherType.DRIZZLE -> "5"
        WeatherType.FREEZING_RAIN -> "6"
        WeatherType.SNOWFALL-> "7"
        else -> throw IllegalArgumentException("Invalid weather type weather")
    }
    return weatherType
}

fun getWeatherType(number: String): WeatherType {
    return when (number) {
        "0" -> WeatherType.SUN
        "1" -> WeatherType.RAIN
        "2" -> WeatherType.SLEET
        "3" -> WeatherType.SNOW
        "4" -> WeatherType.SHOWERS
        "5" -> WeatherType.DRIZZLE
        "6" -> WeatherType.FREEZING_RAIN
        "7" -> WeatherType.SNOWFALL
        else -> throw IllegalArgumentException("Invalid weather type number")
    }
}