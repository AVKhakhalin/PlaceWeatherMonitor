package com.place.weather.monitor.placeweathermonitor.repository.retrofit

import com.place.weather.monitor.placeweathermonitor.api.RetrofitService
import com.place.weather.monitor.placeweathermonitor.model.core.WeatherData
import javax.inject.Inject

class WeatherDataRetrofitImpl @Inject constructor(
    private val retrofitService: RetrofitService,
) : WeatherDataRetrofit {
    override suspend fun getCurrentWeatherData(latitude: Double, longitude: Double
    ): WeatherData {
        return retrofitService.requestCurrentWeatherData(
                    lat = latitude,
                    lon = longitude,
                ).await()
    }
}