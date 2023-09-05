package com.rsupport.core.mapper

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.core.content.ContextCompat
import com.rsupport.ui_component.R
import com.rsupport.weather_util.WeatherType
import com.rsupport.weather_util.getWeatherType

object ResourceMapper {
    fun toWeatherResource(weatherType: String, context: Context): Bitmap {
        val drawableResId = when (getWeatherType(weatherType)) {
            WeatherType.SUN -> R.drawable.sun
            WeatherType.RAIN -> R.drawable.rain
            WeatherType.SLEET -> R.drawable.rain
            WeatherType.SNOW -> R.drawable.snow
            WeatherType.SHOWERS -> R.drawable.storm
            WeatherType.DRIZZLE -> R.drawable.rain
            WeatherType.FREEZING_RAIN -> R.drawable.snow
            WeatherType.SNOWFALL -> R.drawable.snow
        }

        val drawable = ContextCompat.getDrawable(context, drawableResId)
        val originalBitmap = (drawable as BitmapDrawable).bitmap
        return Bitmap.createScaledBitmap(originalBitmap, 100, 100, false)
    }
}