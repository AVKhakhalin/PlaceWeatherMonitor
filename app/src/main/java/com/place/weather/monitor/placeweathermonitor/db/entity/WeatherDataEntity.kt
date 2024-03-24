package com.place.weather.monitor.placeweathermonitor.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "weather_data_entity")
data class WeatherDataEntity (
    @PrimaryKey
    @ColumnInfo(name = "date") val date: Date,
    @ColumnInfo(name = "coord_latitude") val coordLatitude: Double,
    @ColumnInfo(name = "coord_longitude") val coordLongitude: Double,
    @ColumnInfo(name = "weatherShortInfo_id") val weatherShortInfoID: Int,
    @ColumnInfo(name = "weatherShortInfo_main") val weatherShortInfoMain: String,
    @ColumnInfo(name = "weatherShortInfo_description") val weatherShortInfoDescription: String,
    @ColumnInfo(name = "weatherShortInfo_icon") val weatherShortInfoIcon: String,
    @ColumnInfo(name = "base") val base: String,
    @ColumnInfo(name = "weather_main_temp") val weatherMainTemp: Double,
    @ColumnInfo(name = "weather_main_feels_like") val weatherMainFeelsLike: Double,
    @ColumnInfo(name = "weather_main_temp_min") val weatherMainTempMin: Double,
    @ColumnInfo(name = "weather_main_temp_max") val weatherMainTempMax: Double,
    @ColumnInfo(name = "weather_main_pressure") val weatherMainPressure: Int,
    @ColumnInfo(name = "weather_main_humidity") val weatherMainHumidity: Int,
    @ColumnInfo(name = "visibility") val visibility: Int,
    @ColumnInfo(name = "wind_speed") val windSpeed: Double,
    @ColumnInfo(name = "wind_deg") val windDeg: Int,
    @ColumnInfo(name = "clouds_all") val cloudsAll: Int,
    @ColumnInfo(name = "dt") val dt: Int,
    @ColumnInfo(name = "sys_type") val sysType: Int,
    @ColumnInfo(name = "sys_id") val sysID: Long,
    @ColumnInfo(name = "sys_country") val sysCountry: String,
    @ColumnInfo(name = "sys_sunrise") val sysSunrise: Long,
    @ColumnInfo(name = "sys_sunset") val sysSunset: Long,
    @ColumnInfo(name = "timezone") val timezone: Int,
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "cod") val cod: Int,
)