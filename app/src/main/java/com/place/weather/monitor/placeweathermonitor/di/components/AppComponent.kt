package com.place.weather.monitor.placeweathermonitor.di.components

import com.place.weather.monitor.placeweathermonitor.di.modules.AppModule
import com.place.weather.monitor.placeweathermonitor.view.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class
    ]
)
interface AppComponent {
    /** AppComponent */ //region
    fun injectMainActivity(mainActivity: MainActivity)
    //endregion
}