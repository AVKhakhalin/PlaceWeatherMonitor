package com.place.weather.monitor.placeweathermonitor.repository

import com.place.weather.monitor.placeweathermonitor.model.core.WeatherData
import com.place.weather.monitor.placeweathermonitor.model.core.WeatherDataWithDate
import com.place.weather.monitor.placeweathermonitor.model.core.WeatherDataWithDateShortInfo
import java.util.*

interface WeatherRepository {
    // Получение данных о погоде за последние дни
    suspend fun getAllLastData(
        isOnline: Boolean,
        latitude: Double,
        longitude: Double
    ): List<WeatherDataWithDate>

    // Получение сокращённых данных о погоде за последние дни
    suspend fun getAllLastDataShortInfo(
        isOnline: Boolean,
        latitude: Double,
        longitude: Double
    ): List<WeatherDataWithDateShortInfo>

    // Получение текущих данных о погоде
    suspend fun getLastWeatherData(
        isOnline: Boolean,
        latitude: Double,
        longitude: Double
    ): WeatherDataWithDate?

    // Получение данных о погоде за определённую дату
    suspend fun getDataByDate(
        date: Date
    ): WeatherDataWithDate?

    // Сохранение погодных данных
    suspend fun putNewData(
        weatherData: WeatherData
    )
}