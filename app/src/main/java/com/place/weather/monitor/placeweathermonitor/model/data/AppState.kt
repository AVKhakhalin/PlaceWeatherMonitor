package com.place.weather.monitor.placeweathermonitor.model.data

import com.place.weather.monitor.placeweathermonitor.model.core.WeatherData

sealed class AppState {
    data class Success(val inputData: WeatherData?): AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()
}