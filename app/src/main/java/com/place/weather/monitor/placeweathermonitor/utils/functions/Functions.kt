package com.place.weather.monitor.placeweathermonitor.utils.functions

import android.annotation.SuppressLint
import android.util.Log
import com.place.weather.monitor.placeweathermonitor.db.entity.WeatherDataEntity
import com.place.weather.monitor.placeweathermonitor.model.core.*
import com.place.weather.monitor.placeweathermonitor.utils.DATE_FORMAT
import com.place.weather.monitor.placeweathermonitor.utils.ERROR_CODE
import com.place.weather.monitor.placeweathermonitor.utils.ERROR_TAG
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun List<WeatherDataEntity>.convertToListWeatherDataWithDate() : List<WeatherDataWithDate> {
    val listWeatherData: MutableList<WeatherDataWithDate> = mutableListOf()
    this.forEach {
        listWeatherData.add(it.convertToWeatherDataWithDate())
    }
    return listWeatherData
}

fun WeatherDataEntity.convertToWeatherDataWithDate() : WeatherDataWithDate {
    return WeatherDataWithDate(
        date = this.date.time,
        coord = Coord(
            lon = this.coordLongitude,
            lat = this.coordLatitude,
        ),
        weather = listOf<WeatherShortInfo>(
            WeatherShortInfo(
                id = this.weatherShortInfoID,
                main = this.weatherShortInfoMain,
                description = this.weatherShortInfoDescription,
                icon = this.weatherShortInfoIcon,
            ),
        ),
        base = this.base,
        main = WeatherMain(
            temp = this.weatherMainTemp,
            feels_like = this.weatherMainFeelsLike,
            temp_min = this.weatherMainTempMin,
            temp_max = this.weatherMainTempMax,
            pressure = this.weatherMainPressure,
            humidity = this.weatherMainHumidity,
        ),
        visibility = this.visibility,
        wind = Wind(
            speed = this.windSpeed,
            deg = this.windDeg,
        ),
        clouds = Clouds(
            all = this.cloudsAll,
        ),
        dt = this.dt,
        sys = Sys(
            type = this.sysType,
            id = this.sysID,
            country = this.sysCountry,
            sunrise = this.sysSunrise,
            sunset = this.sysSunset,
        ),
        timezone = this.timezone,
        id = this.id,
        cod = this.cod,
        name = this.name,
    )
}

fun WeatherData.convertToWeatherDataWithDate() : WeatherDataWithDate {
    val weatherShortInfo : WeatherShortInfo =
        if (this.weather.isNotEmpty()) this.weather[0]
        else WeatherShortInfo(
            id = ERROR_CODE,
            main = "$ERROR_CODE",
            description = "$ERROR_CODE",
            icon = "$ERROR_CODE"
        )

    return WeatherDataWithDate(
        date = getCurrentDate().time,
        coord = Coord(
            lon = this.coord.lon,
            lat = this.coord.lat,
        ),
        weather = listOf<WeatherShortInfo>(
            WeatherShortInfo(
                id = weatherShortInfo.id,
                main = weatherShortInfo.main,
                description = weatherShortInfo.description,
                icon = weatherShortInfo.icon,
            ),
        ),
        base = this.base,
        main = WeatherMain(
            temp = this.main.temp,
            feels_like = this.main.feels_like,
            temp_min = this.main.temp_min,
            temp_max = this.main.temp_max,
            pressure = this.main.pressure,
            humidity = this.main.humidity,
        ),
        visibility = this.visibility,
        wind = Wind(
            speed = this.wind.speed,
            deg = this.wind.deg,
        ),
        clouds = Clouds(
            all = this.clouds.all,
        ),
        dt = this.dt,
        sys = Sys(
            type = this.sys.type,
            id = this.sys.id,
            country = this.sys.country,
            sunrise = this.sys.sunrise,
            sunset = this.sys.sunset,
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
            main = "$ERROR_CODE",
            description = "$ERROR_CODE",
            icon = "$ERROR_CODE"
        )

    return WeatherDataEntity(
        date = getCurrentDate(),
        coordLatitude = this.coord.lat,
        coordLongitude = this.coord.lon,
        weatherShortInfoID = weatherShortInfo.id,
        weatherShortInfoMain = weatherShortInfo.main,
        weatherShortInfoDescription = weatherShortInfo.description,
        weatherShortInfoIcon = weatherShortInfo.icon,
        base = this.base,
        weatherMainTemp = this.main.temp,
        weatherMainFeelsLike = this.main.feels_like,
        weatherMainTempMin = this.main.temp_min,
        weatherMainTempMax = this.main.temp_max,
        weatherMainPressure = this.main.pressure,
        weatherMainHumidity = this.main.humidity,
        visibility = this.visibility,
        windSpeed = this.wind.speed,
        windDeg = this.wind.deg,
        cloudsAll = this.clouds.all,
        dt = this.dt,
        sysType = this.sys.type,
        sysID = this.sys.id,
        sysCountry = this.sys.country,
        sysSunrise = this.sys.sunrise,
        sysSunset = this.sys.sunset,
        timezone = this.timezone,
        id = this.id,
        name = this.name,
        cod = this.cod,
    )
}

@SuppressLint("SimpleDateFormat")
fun getCurrentDate(): Date {
    val formatter = SimpleDateFormat(DATE_FORMAT)
    val currentDate: Date = Date()
    val current = formatter.format(currentDate)
    return Date(convertServerDateToLong(current))
}

@SuppressLint("SimpleDateFormat")
fun convertServerDateToLong(date: String): Long {
    val df = SimpleDateFormat(DATE_FORMAT)
    var result: Long = 0
    try {
        result = df.parse(date).time
    } catch (error: ParseException) {
        Log.d(ERROR_TAG, "Ошибка: Ошибочный формат даты")
    }
    return result
}



