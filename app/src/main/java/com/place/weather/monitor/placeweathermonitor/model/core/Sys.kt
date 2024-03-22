package com.place.weather.monitor.placeweathermonitor.model.core

data class Sys (
    val type: Int,
    val id: Long,
    val country: String,
    val sunrise: Long,
    val sunset: Long,
)