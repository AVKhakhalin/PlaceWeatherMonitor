package com.place.weather.monitor.placeweathermonitor.model.core

import com.google.gson.annotations.SerializedName

data class Wind (
    @SerializedName("speed")
    val speed: Double,
    @SerializedName("deg")
    val deg: Int,
)