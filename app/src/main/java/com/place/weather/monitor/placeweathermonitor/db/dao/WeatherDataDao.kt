package com.place.weather.monitor.placeweathermonitor.db.dao

import androidx.room.*
import com.place.weather.monitor.placeweathermonitor.db.entity.WeatherDataEntity
import java.util.Date

@Dao
interface WeatherDataDao {
    @Query("SELECT * from weatherData WHERE CAST((date / 1000) AS INTEGER) BETWEEN strftime('%s','now','-5 days') AND strftime('%s','now')  ORDER BY date DESC;")
    suspend fun getAllLastData(): List<WeatherDataEntity>

    @Query("SELECT * from weatherData WHERE date=:date;")
    suspend fun getDataByDate(date: Date): WeatherDataEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(weatherDataEntity: WeatherDataEntity)

    @Delete
    suspend fun deleteData(weatherDataEntity: WeatherDataEntity)
}