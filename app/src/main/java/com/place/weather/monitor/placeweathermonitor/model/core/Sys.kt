package com.place.weather.monitor.placeweathermonitor.model.core

import com.google.gson.annotations.SerializedName

data class Sys (
    @SerializedName("type")
    val type: Int,
    @SerializedName("id")
    val id: Long,
    @SerializedName("country")
    val country: String,
    @SerializedName("sunrise")
    val sunrise: Long,
    @SerializedName("sunset")
    val sunset: Long,
)