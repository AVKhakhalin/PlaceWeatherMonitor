package com.place.weather.monitor.placeweathermonitor.di.scope.containers

import com.place.weather.monitor.placeweathermonitor.di.components.DetailWeatherSubcomponent

interface DetailWeatherScopeContainer {

    fun initDetailWeatherSubcomponent(): DetailWeatherSubcomponent

    fun destroyDetailWeatherSubcomponent()
}