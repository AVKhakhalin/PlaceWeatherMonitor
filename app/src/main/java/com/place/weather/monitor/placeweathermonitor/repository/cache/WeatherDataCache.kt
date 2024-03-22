package com.place.weather.monitor.placeweathermonitor.repository.cache

import com.place.weather.monitor.placeweathermonitor.model.core.WeatherData
import java.util.Date

interface WeatherDataCache {
    suspend fun getAllLastData(): List<WeatherData>
    suspend fun getDataByDate(date: Date): WeatherData
    suspend fun putNewData(weatherData: WeatherData)
}