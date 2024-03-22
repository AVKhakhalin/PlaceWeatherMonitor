package com.place.weather.monitor.placeweathermonitor.di.scope.containers

import com.place.weather.monitor.placeweathermonitor.di.components.HomePageSubcomponent

interface HomePageScopeContainer {

    fun initHomePageSubcomponent(): HomePageSubcomponent

    fun destroyHomePageSubcomponent()
}