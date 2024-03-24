package com.place.weather.monitor.placeweathermonitor.model.core

import com.google.gson.annotations.SerializedName

// Данные для домашней страницы
data class WeatherDataWithDateShortInfo(
    @SerializedName("date")
    val date: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("all")
    val all: Int,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("temp_min")
    val temp_min: Double,
    @SerializedName("temp_max")
    val temp_max: Double,
    @SerializedName("speed")
    val speed: Double,
)