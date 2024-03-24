package com.place.weather.monitor.placeweathermonitor.api

import com.place.weather.monitor.placeweathermonitor.BuildConfig
import com.place.weather.monitor.placeweathermonitor.model.core.WeatherData
import com.place.weather.monitor.placeweathermonitor.utils.API_LANGUAGE
import com.place.weather.monitor.placeweathermonitor.utils.API_UNITS
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface RetrofitService {
    @Headers("Content-Type: application/json")
    @GET("weather")
    fun requestCurrentWeatherData(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String = BuildConfig.API_KEY,
        @Query("lang") lang: String = API_LANGUAGE,
        @Query("units") units: String = API_UNITS,
    ): Deferred<WeatherData>
}