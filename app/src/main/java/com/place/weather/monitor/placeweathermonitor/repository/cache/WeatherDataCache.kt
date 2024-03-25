package com.place.weather.monitor.placeweathermonitor.repository.cache

import com.place.weather.monitor.placeweathermonitor.model.core.WeatherData
import com.place.weather.monitor.placeweathermonitor.model.core.WeatherDataWithDate
import com.place.weather.monitor.placeweathermonitor.model.core.WeatherDataWithDateShortInfo
import java.util.*

interface WeatherDataCache {
    suspend fun getAllLastData(lastDate: Date): List<WeatherDataWithDate>
    suspend fun getAllLastDataShortInfo(lastDate: Date): List<WeatherDataWithDateShortInfo>
    suspend fun getDataByDate(date: Date): WeatherDataWithDate?
    suspend fun putNewData(weatherData: WeatherData)
}