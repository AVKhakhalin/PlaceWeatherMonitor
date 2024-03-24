package com.place.weather.monitor.placeweathermonitor.repository.cache

import com.place.weather.monitor.placeweathermonitor.model.core.WeatherData
import com.place.weather.monitor.placeweathermonitor.model.core.WeatherDataWithDate
import java.util.*

interface WeatherDataCache {
    suspend fun getAllLastData(lastDate: Date): List<WeatherDataWithDate>
    suspend fun getDataByDate(date: Date): WeatherDataWithDate?
    suspend fun putNewData(weatherData: WeatherData)
}