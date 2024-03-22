package com.place.weather.monitor.placeweathermonitor.repository.retrofit

import com.place.weather.monitor.placeweathermonitor.model.core.WeatherData

interface WeatherDataRetrofit {
    suspend fun getCurrentWeatherData(latitude: Double, longitude: Double): WeatherData
}