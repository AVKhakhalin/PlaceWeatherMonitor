package com.place.weather.monitor.placeweathermonitor.di.components

import com.place.weather.monitor.placeweathermonitor.di.modules.HomePageModule
import com.place.weather.monitor.placeweathermonitor.di.scope.HomePageScope
import dagger.Subcomponent

@HomePageScope
@Subcomponent(
    modules = [
        HomePageModule::class
    ]
)
interface HomePageSubcomponent {
}