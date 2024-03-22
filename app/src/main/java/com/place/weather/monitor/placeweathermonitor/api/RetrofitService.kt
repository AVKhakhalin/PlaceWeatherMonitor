package com.place.weather.monitor.placeweathermonitor.api

import com.place.weather.monitor.placeweathermonitor.BuildConfig
import com.place.weather.monitor.placeweathermonitor.model.core.WeatherData
import com.place.weather.monitor.placeweathermonitor.utils.API_LANGUAGE
import com.place.weather.monitor.placeweathermonitor.utils.API_UNITS
import kotlinx.coroutines.Deferred
import retrofit2.http.Field
import retrofit2.http.Headers
import retrofit2.http.POST

interface RetrofitService {
    @Headers("Content-Type: application/json")
    @POST("/weather")
    fun requestCurrentWeatherData(
        @Field("lat") lat: Double,
        @Field("lon") lon: Double,
        @Field("appid") appid: String = BuildConfig.API_KEY,
        @Field("lang") lang: String = API_LANGUAGE,
        @Field("units") units: String = API_UNITS,
    ): Deferred<WeatherData>
}