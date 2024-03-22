package com.place.weather.monitor.placeweathermonitor.repository.cache

import com.place.weather.monitor.placeweathermonitor.db.AppDatabase
import com.place.weather.monitor.placeweathermonitor.db.entity.WeatherDataEntity
import com.place.weather.monitor.placeweathermonitor.model.core.WeatherData
import com.place.weather.monitor.placeweathermonitor.utils.functions.convertToListWeatherData
import com.place.weather.monitor.placeweathermonitor.utils.functions.convertToWeatherData
import com.place.weather.monitor.placeweathermonitor.utils.functions.convertToWeatherDataEntity
import java.util.*
import javax.inject.Inject

class WeatherDataCacheImpl @Inject constructor(
    private val db: AppDatabase
) : WeatherDataCache {
    override suspend fun getAllLastData(): List<WeatherData> {
        return db.weatherDataDao.getAllLastData().convertToListWeatherData()
    }

    override suspend fun getDataByDate(date: Date): WeatherData {
        return db.weatherDataDao.getDataByDate(date).convertToWeatherData()
    }

    override suspend fun putNewData(weatherData: WeatherData) {
        db.weatherDataDao.insertData(weatherData.convertToWeatherDataEntity())
    }
}