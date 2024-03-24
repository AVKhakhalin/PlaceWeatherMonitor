package com.place.weather.monitor.placeweathermonitor.repository

import com.place.weather.monitor.placeweathermonitor.model.core.WeatherData
import com.place.weather.monitor.placeweathermonitor.model.core.WeatherDataWithDate
import com.place.weather.monitor.placeweathermonitor.repository.cache.WeatherDataCache
import com.place.weather.monitor.placeweathermonitor.repository.retrofit.WeatherDataRetrofit
import com.place.weather.monitor.placeweathermonitor.utils.functions.convertToWeatherDataWithDate
import com.place.weather.monitor.placeweathermonitor.utils.functions.getCurrentDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherDataRetrofit: WeatherDataRetrofit,
    private val weatherDataCache: WeatherDataCache,
): WeatherRepository {
    override suspend fun getAllLastData(
        isOnline: Boolean,
        latitude: Double,
        longitude: Double,
    ): List<WeatherDataWithDate> {
//        val counterContext = newSingleThreadContext("GetDataAndSaveToDB")
//        return withContext(counterContext) {
//            var weatherData: WeatherData? = null
//            // Получение данных о погоде (если сеть доступна)
//            if (isOnline) {
////                weatherData = weatherDataRetrofit.getCurrentWeatherData(
////                    latitude = latitude,
////                    longitude = longitude,
////                )
////                // Сохранение полученных данных о погоде в базу данных
////                weatherData?.let {
////                    weatherDataCache.putNewData(weatherData)
////                }
//
//                weatherDataRetrofit.getCurrentWeatherData(
//                    latitude = latitude,
//                    longitude = longitude,
//                )?.let {
//                    Log.d("DECODE_INV", "РЕЗУЛЬТАТ: $it")
//                    weatherDataCache.putNewData(it)
//                }
//            }
//            // Полученных данных о погоде за последние дни
//            return@withContext weatherDataCache.getAllLastData()
//        }

        val responseRetrofit: WeatherData = withContext(Dispatchers.IO) {
                weatherDataRetrofit.getCurrentWeatherData(
                    latitude = latitude,
                    longitude = longitude,
                )
        }
        when(responseRetrofit.cod) {
            200 -> {
                weatherDataCache.putNewData(responseRetrofit)
            }
        }
        return weatherDataCache.getAllLastData()
    }

    override suspend fun getLastWeatherData(
        isOnline: Boolean,
        latitude: Double,
        longitude: Double
    ): WeatherDataWithDate? {
        return if (isOnline) {
            weatherDataRetrofit.getCurrentWeatherData(
                latitude = latitude,
                longitude = longitude
            ).convertToWeatherDataWithDate()
        } else {
            weatherDataCache.getDataByDate(getCurrentDate())
        }
    }

    override suspend fun getDataByDate(date: Date): WeatherDataWithDate? {
        return weatherDataCache.getDataByDate(date)
    }

    override suspend fun putNewData(weatherData: WeatherData) {
        weatherDataCache.putNewData(weatherData)
    }
}