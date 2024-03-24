package com.place.weather.monitor.placeweathermonitor.db.dao

import androidx.room.*
import com.place.weather.monitor.placeweathermonitor.db.entity.WeatherDataEntity
import java.util.*

@Dao
interface WeatherDataDao {
    @Query("SELECT * FROM weather_data_entity WHERE date >= :lastDate ORDER BY date DESC")
    suspend fun getAllLastData(lastDate: Date): List<WeatherDataEntity>

    @Query("SELECT * from weather_data_entity WHERE date=:date")
    suspend fun getDataByDate(date: Date): WeatherDataEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(weatherDataEntity: WeatherDataEntity)

    @Delete
    suspend fun deleteData(weatherDataEntity: WeatherDataEntity)

    @Query("DELETE FROM weather_data_entity")
    suspend fun deleteAllData()
}