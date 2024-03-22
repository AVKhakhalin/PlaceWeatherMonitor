package com.place.weather.monitor.placeweathermonitor.di.components

import com.place.weather.monitor.placeweathermonitor.di.modules.DetailWeatherModule
import com.place.weather.monitor.placeweathermonitor.di.scope.DetailWeatherScope
import dagger.Subcomponent

@DetailWeatherScope
@Subcomponent(
    modules = [
        DetailWeatherModule::class
    ]
)
interface DetailWeatherSubcomponent {

}