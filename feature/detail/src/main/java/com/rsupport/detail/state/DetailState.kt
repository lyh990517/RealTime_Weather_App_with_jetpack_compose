package com.rsupport.detail.state

import android.graphics.Bitmap
import com.rsupport.core.model.WeatherUiModel

sealed class DetailState {
    object Uninitialized : DetailState()

    data class Success(val weatherUiModel: WeatherUiModel, val bitmap: Bitmap) : DetailState()
}