package com.place.weather.monitor.placeweathermonitor.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "weather_data_entity")
data class WeatherShortDataEntity (
    @PrimaryKey
    @ColumnInfo(name = "date") val date: Date,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "clouds_all") val cloudsAll: Int,
    @ColumnInfo(name = "weather_main_humidity") val weatherMainHumidity: Int,
    @ColumnInfo(name = "weather_main_pressure") val weatherMainPressure: Int,
    @ColumnInfo(name = "weather_main_temp_min") val weatherMainTempMin: Double,
    @ColumnInfo(name = "weather_main_temp_max") val weatherMainTempMax: Double,
    @ColumnInfo(name = "wind_speed") val windSpeed: Double,
)