package com.place.weather.monitor.placeweathermonitor.utils.functions

import android.annotation.SuppressLint
import android.util.Log
import com.place.weather.monitor.placeweathermonitor.db.entity.WeatherDataEntity
import com.place.weather.monitor.placeweathermonitor.model.core.*
import com.place.weather.monitor.placeweathermonitor.utils.DATE_FORMAT
import com.place.weather.monitor.placeweathermonitor.utils.ERROR_CODE
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun List<WeatherDataEntity>.convertToListWeatherData() : List<WeatherData> {
    val listWeatherData: MutableList<WeatherData> = mutableListOf()
    this.forEach {
        listWeatherData.add(it.convertToWeatherData())
    }
    return listWeatherData
}

fun WeatherDataEntity.convertToWeatherData() : WeatherData {
    return WeatherData(
        coord = Coord(
            lon = this.coord_longitude,
            lat = this.coord_latitude,
        ),
        weather = listOf<WeatherShortInfo>(
            WeatherShortInfo(
                id = this.weatherShortInfo_id,
                main = Rain(
                  `1h` = this.weatherShortInfo_main_rain_1h,
                ),
                description = this.weatherShortInfo_description,
                icon = this.weatherShortInfo_icon,
            ),
        ),
        base = this.base,
        main = WeatherMain(
            temp = this.weatherMain_temp,
            feels_like = this.weatherMain_feels_like,
            temp_min = this.weatherMain_temp_min,
            temp_max = this.weatherMain_temp_max,
            pressure = this.weatherMain_pressure,
            humidity = this.weatherMain_humidity,
            sea_level = this.weatherMain_sea_level,
            grnd_level = this.weatherMain_grnd_level,
        ),
        visibility = this.visibility,
        wind = Wind(
            speed = this.wind_speed,
            deg = this.wind_deg,
            gust = this.wind_gust,
        ),
        rain = Rain(
            `1h` = this.rain_1h,
        ),
        clouds = Clouds(
            all = this.clouds_all,
        ),
        dt = this.dt,
        sys = Sys(
            type = this.sys_type,
            id = this.sys_id,
            country = this.sys_country,
            sunrise = this.sys_sunrise,
            sunset = this.sys_sunset,
        ),
        timezone = this.timezone,
        id = this.id,
        cod = this.cod,
        name = this.name,
    )
}

@SuppressLint("SimpleDateFormat")
fun WeatherData.convertToWeatherDataEntity() : WeatherDataEntity {
    val weatherShortInfo : WeatherShortInfo =
        if (this.weather.isNotEmpty()) this.weather[0]
        else WeatherShortInfo(
            id = ERROR_CODE,
            main = Rain(
                `1h` = ERROR_CODE.toDouble()
            ),
            description = "$ERROR_CODE",
            icon = "$ERROR_CODE"
        )
    val formatter = SimpleDateFormat(DATE_FORMAT)
    val currentDate: Date = Date()
    val current = formatter.format(currentDate)

    return WeatherDataEntity(
        date = Date(convertServerDateToLong(current)),
        coord_latitude = this.coord.lat,
        coord_longitude = this.coord.lon,
        weatherShortInfo_id = weatherShortInfo.id,
        weatherShortInfo_main_rain_1h = weatherShortInfo.main.`1h`,
        weatherShortInfo_description = weatherShortInfo.description,
        weatherShortInfo_icon = weatherShortInfo.icon,
        base = this.base,
        weatherMain_temp = this.main.temp,
        weatherMain_feels_like = this.main.feels_like,
        weatherMain_temp_min = this.main.temp_min,
        weatherMain_temp_max = this.main.temp_max,
        weatherMain_pressure = this.main.pressure,
        weatherMain_humidity = this.main.humidity,
        weatherMain_sea_level = this.main.sea_level,
        weatherMain_grnd_level = this.main.grnd_level,
        visibility = this.visibility,
        wind_speed = this.wind.speed,
        wind_deg = this.wind.deg,
        wind_gust = this.wind.gust,
        rain_1h = this.rain.`1h`,
        clouds_all = this.clouds.all,
        dt = this.dt,
        sys_type = this.sys.type,
        sys_id = this.sys.id,
        sys_country = this.sys.country,
        sys_sunrise = this.sys.sunrise,
        sys_sunset = this.sys.sunset,
        timezone = this.timezone,
        id = this.id,
        name = this.name,
        cod = this.cod,
    )
}

@SuppressLint("SimpleDateFormat")
fun convertServerDateToLong(date: String): Long {
    val df = SimpleDateFormat(DATE_FORMAT)
    var result: Long = 0
    try {
        result = df.parse(date).time
    } catch (error: ParseException) {
        Log.d("mylogs", "Ошибка: Ошибочный формат даты")
    }
    return result
}



