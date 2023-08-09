package com.rsupport.map.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rsupport.core.model.ModelWrapper
import com.rsupport.core.model.WeatherUiModel
import com.rsupport.core.repository.IWeatherRepository
import com.rsupport.map.presentation.state.MapState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val iWeatherRepository: IWeatherRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow<MapState>(MapState.Loading)
    val uiState = _uiState
    fun fetchWeather(
        pageNo: Int,
        numOfRows: Int,
        dataType: String,
        baseDate: String,
        baseTime: String,
        nx: Int,
        ny: Int
    ) = viewModelScope.launch {
        val result =
            iWeatherRepository.getWeather(pageNo, numOfRows, dataType, baseDate, baseTime, nx, ny)
        when (result) {
            is ModelWrapper.Success -> {
                val weatherUiModel = result.model as WeatherUiModel
                _uiState.value = MapState.Success(weatherUiModel)
            }

            is ModelWrapper.Fail -> {
                _uiState.value = MapState.Fail(result.error)
            }
        }
    }
}