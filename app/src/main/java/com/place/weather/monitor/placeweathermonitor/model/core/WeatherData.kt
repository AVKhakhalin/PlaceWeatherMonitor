package com.place.weather.monitor.placeweathermonitor.model.core

data class WeatherData(
    val coord: Coord,
    val weather: List<WeatherShortInfo>,
    val base: String,
    val main: WeatherMain,
    val visibility: Int,
    val wind: Wind,
    val rain: Rain,
    val clouds: Clouds,
    val dt: Int,
    val sys: Sys,
    val timezone: Int,
    val id: Int,
    val name: String,
    val cod: Int,
)