package com.place.weather.monitor.placeweathermonitor.repository

import com.place.weather.monitor.placeweathermonitor.model.core.WeatherData

interface WeatherRepository {
    suspend fun getAllLastData(
        isOnline: Boolean,
        latitude: Double,
        longitude: Double
    ): List<WeatherData>
}