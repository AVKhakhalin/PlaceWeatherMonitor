package com.place.weather.monitor.placeweathermonitor.utils.functions

import android.graphics.Color
import android.util.Log
import com.place.weather.monitor.placeweathermonitor.db.entity.WeatherDataEntity
import com.place.weather.monitor.placeweathermonitor.db.entity.WeatherShortDataEntity
import com.place.weather.monitor.placeweathermonitor.model.core.*
import com.place.weather.monitor.placeweathermonitor.utils.ALPHA_NOT_OPAQUE
import com.place.weather.monitor.placeweathermonitor.utils.ERROR_CODE
import com.place.weather.monitor.placeweathermonitor.utils.NUMBER_LAST_DAYS
import java.util.*

fun List<WeatherShortDataEntity>.convertToListWeatherShortInfo() :
        List<WeatherDataWithDateShortInfo> {
    val listWeatherDataWithDateShortInfo:
            MutableList<WeatherDataWithDateShortInfo> = mutableListOf()
    this.forEach {
        listWeatherDataWithDateShortInfo.add(it.convertToWeatherShortInfo())
    }
    return listWeatherDataWithDateShortInfo
}

fun WeatherShortDataEntity.convertToWeatherShortInfo() : WeatherDataWithDateShortInfo {
    return WeatherDataWithDateShortInfo(
        date = this.date.time,
        name = this.name,
        all = this.cloudsAll,
        humidity = this.weatherMainHumidity,
        pressure = this.weatherMainPressure,
        temp_min = this.weatherMainTempMin,
        temp_max = this.weatherMainTempMax,
        speed = this.windSpeed,
    )
}

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

// Получение текущей даты
fun getCurrentDate(): Date {
    return getCalendarWithoutTime().time
}

// Получение даты со сдвигом на заданное количество дней
fun getLastDate(numberLastDays: Int): Date {
    val calendar: Calendar = getCalendarWithoutTime()
    calendar.add(Calendar.DAY_OF_WEEK, -numberLastDays)
    return calendar.time
}

// Получение класса Calendar с обнулёнными часами, минутами, секундами и миллисекундами
fun getCalendarWithoutTime(): Calendar {
    val calendar: Calendar = Calendar.getInstance()
    calendar[Calendar.HOUR_OF_DAY] = 0
    calendar[Calendar.MINUTE] = 0
    calendar[Calendar.SECOND] = 0
    calendar[Calendar.MILLISECOND] = 0
    return calendar
}

fun Int.convertToColor(): Int {
// Схема изменения цвета:
// Rating    R   G  B
//    0  -  201  35 35
//    25 -  174 136 87
//    50 -  167 174 87
//   100 -   42 166 40
    var red: Int = 0
    var green: Int = 0
    var blue: Int = 0
    when (this) {
        in 0..25 -> {
            red = 201 - 27 / 25
            green = 35 + this * 101 / 25
            blue = 35 + this * 52 / 25
        }
        in 25..50 -> {
            red = 174 - (this - 25) * 7 / 25
            green = 136 + (this - 25) * 38 / 25
            blue = 87
        }
        in 50..100 -> {
            red = 167 - (this - 50) * 125 / 50
            green = 174 - (this - 50) * 8 / 50
            blue = 87 - (this - 50) * 47 / 50
        }
    }
    return Color.argb(ALPHA_NOT_OPAQUE, red, green, blue)
}