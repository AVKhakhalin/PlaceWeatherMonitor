package com.place.weather.monitor.placeweathermonitor.repository

import com.place.weather.monitor.placeweathermonitor.model.core.WeatherData
import com.place.weather.monitor.placeweathermonitor.model.core.WeatherDataWithDate
import java.util.*

interface WeatherRepository {
    // Получение данных о погоде за последние дни
    suspend fun getAllLastData(
        isOnline: Boolean,
        latitude: Double,
        longitude: Double
    ): List<WeatherDataWithDate>

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