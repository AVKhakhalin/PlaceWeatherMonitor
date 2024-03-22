package com.place.weather.monitor.placeweathermonitor.repository

import com.place.weather.monitor.placeweathermonitor.model.core.WeatherData
import com.place.weather.monitor.placeweathermonitor.repository.cache.WeatherDataCache
import com.place.weather.monitor.placeweathermonitor.repository.retrofit.WeatherDataRetrofit
import com.place.weather.monitor.placeweathermonitor.utils.functions.convertToListWeatherData
import com.place.weather.monitor.placeweathermonitor.utils.functions.convertToWeatherDataEntity
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherDataRetrofit: WeatherDataRetrofit,
    private val weatherDataCache: WeatherDataCache,
): WeatherRepository {
    override suspend fun getAllLastData(
        isOnline: Boolean,
        latitude: Double,
        longitude: Double,
    ): List<WeatherData> {
        val counterContext = newSingleThreadContext("GetDataAndSaveToDB")
        return withContext(counterContext) {
            var weatherData: WeatherData? = null
            // Получение данных о погоде (если сеть доступна)
            if (isOnline) {
                weatherData = weatherDataRetrofit.getCurrentWeatherData(
                    latitude = latitude,
                    longitude = longitude,
                )
                // Сохранение полученных данных о погоде в базу данных
                weatherDataCache.putNewData(weatherData)
            }
            // Полученных данных о погоде за последние дни
            return@withContext weatherDataCache.getAllLastData()
        }
    }
}