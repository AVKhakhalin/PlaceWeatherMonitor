package com.place.weather.monitor.placeweathermonitor.model.data

import com.place.weather.monitor.placeweathermonitor.model.core.WeatherDataWithDate
import com.place.weather.monitor.placeweathermonitor.model.core.WeatherDataWithDateShortInfo

sealed class AppState {
    data class SuccessGetLastKnownWeatherDataShortInfo(
        val inputData: List<WeatherDataWithDateShortInfo>): AppState()
    data class SuccessGetDetailedWeatherData(val inputData: WeatherDataWithDate?): AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()
}