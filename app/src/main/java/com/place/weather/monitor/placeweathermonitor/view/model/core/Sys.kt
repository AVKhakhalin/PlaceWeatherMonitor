package com.place.weather.monitor.placeweathermonitor.view.model.core

data class Sys (
    val type: Int,
    val id: Long,
    val country: String,
    val sunrise: Long,
    val sunset: Long,
)