package com.rsupport.detail.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import com.rsupport.core.model.WeatherUiModel
import com.rsupport.detail.state.DetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow<DetailState>(DetailState.Uninitialized)
    val uiState = _uiState

    fun saveWeather(weatherUiModel: WeatherUiModel, bitmap: Bitmap) {
        _uiState.value = DetailState.Success(weatherUiModel, bitmap)
    }
}