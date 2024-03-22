package com.place.weather.monitor.placeweathermonitor.di.components

import com.place.weather.monitor.placeweathermonitor.di.modules.AppModule
import com.place.weather.monitor.placeweathermonitor.di.modules.DbModule
import com.place.weather.monitor.placeweathermonitor.di.modules.NetworkModule
import com.place.weather.monitor.placeweathermonitor.view.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DbModule::class,
        NetworkModule::class,
    ]
)
interface AppComponent {
    /** Activity */ //region
    fun injectMainActivity(mainActivity: MainActivity)
    //endregion

    /** Fragments */ //region
    fun getDetailWeatherSubcomponent() : DetailWeatherSubcomponent
    fun getHomePageSubcomponent() : HomePageSubcomponent
    //endregion
}