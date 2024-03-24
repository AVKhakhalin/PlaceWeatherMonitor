package com.place.weather.monitor.placeweathermonitor.model.data

import com.place.weather.monitor.placeweathermonitor.model.core.WeatherDataWithDate

sealed class AppState {
    data class SuccessGetLastKnownWeatherData(val inputData: List<WeatherDataWithDate>): AppState()
    data class SuccessGetDetailedWeatherData(val inputData: WeatherDataWithDate?): AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()
}