package com.place.weather.monitor.placeweathermonitor.repository.cache

import com.place.weather.monitor.placeweathermonitor.db.AppDatabase
import com.place.weather.monitor.placeweathermonitor.model.core.WeatherData
import com.place.weather.monitor.placeweathermonitor.model.core.WeatherDataWithDate
import com.place.weather.monitor.placeweathermonitor.utils.functions.convertToListWeatherDataWithDate
import com.place.weather.monitor.placeweathermonitor.utils.functions.convertToWeatherDataEntity
import com.place.weather.monitor.placeweathermonitor.utils.functions.convertToWeatherDataWithDate
import java.util.*
import javax.inject.Inject

class WeatherDataCacheImpl @Inject constructor(
    private val db: AppDatabase
) : WeatherDataCache {
    override suspend fun getAllLastData(): List<WeatherDataWithDate> {
        return db.weatherDataDao.getAllLastData().convertToListWeatherDataWithDate()
    }

    override suspend fun getDataByDate(date: Date): WeatherDataWithDate? {
        db.weatherDataDao.getDataByDate(date)?.let {
            return it.convertToWeatherDataWithDate()
        }
        return null
    }

    override suspend fun putNewData(weatherData: WeatherData) {
        db.weatherDataDao.insertData(weatherData.convertToWeatherDataEntity())
    }
}