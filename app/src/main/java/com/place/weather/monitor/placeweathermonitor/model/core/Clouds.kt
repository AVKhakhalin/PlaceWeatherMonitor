package com.place.weather.monitor.placeweathermonitor.model.core

import com.google.gson.annotations.SerializedName

data class Clouds(
    @SerializedName("all")
    val all: Int,
)