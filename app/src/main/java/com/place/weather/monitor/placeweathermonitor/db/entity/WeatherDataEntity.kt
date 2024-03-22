package com.place.weather.monitor.placeweathermonitor.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "weatherData")
data class WeatherDataEntity (
    @PrimaryKey
    val date: Date,
    val coord_latitude: Double,
    val coord_longitude: Double,
    val weatherShortInfo_id : Int,
    val weatherShortInfo_main_rain_1h : Double,
    val weatherShortInfo_description : String,
    val weatherShortInfo_icon : String,
    val base: String,
    val weatherMain_temp: Double,
    val weatherMain_feels_like: Double,
    val weatherMain_temp_min: Double,
    val weatherMain_temp_max: Double,
    val weatherMain_pressure: Int,
    val weatherMain_humidity: Int,
    val weatherMain_sea_level: Int,
    val weatherMain_grnd_level: Int,
    val visibility: Int,
    val wind_speed: Double,
    val wind_deg: Int,
    val wind_gust: Double,
    val rain_1h: Double,
    val clouds_all: Int,
    val dt: Int,
    val sys_type: Int,
    val sys_id: Long,
    val sys_country: String,
    val sys_sunrise: Long,
    val sys_sunset: Long,
    val timezone: Int,
    val id: Int,
    val name: String,
    val cod: Int,
)