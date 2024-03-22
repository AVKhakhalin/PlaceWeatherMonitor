package com.place.weather.monitor.placeweathermonitor.app

import android.app.Application
import com.place.weather.monitor.placeweathermonitor.di.components.AppComponent
import com.place.weather.monitor.placeweathermonitor.di.components.DaggerAppComponent
import com.place.weather.monitor.placeweathermonitor.di.components.DetailWeatherSubcomponent
import com.place.weather.monitor.placeweathermonitor.di.components.HomePageSubcomponent
import com.place.weather.monitor.placeweathermonitor.di.modules.AppModule
import com.place.weather.monitor.placeweathermonitor.di.scope.containers.DetailWeatherScopeContainer
import com.place.weather.monitor.placeweathermonitor.di.scope.containers.HomePageScopeContainer

class App: Application(), DetailWeatherScopeContainer, HomePageScopeContainer {
    /** Исходные данные */ //region
    // appComponent
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
    //endregion

    var detailWeatherSubcomponent: DetailWeatherSubcomponent? = null
    var homePageSubcomponent: HomePageSubcomponent? = null

    override fun onCreate() {
        super.onCreate()
        _instance = this
    }

    /** Методы DetailWeatherScopeContainer */ //region
    override fun initDetailWeatherSubcomponent() =
        appComponent.getDetailWeatherSubcomponent().also {
            detailWeatherSubcomponent = it
    }

    override fun destroyDetailWeatherSubcomponent() {
        detailWeatherSubcomponent = null
    }
    //endregion

    /** Методы HomePageScopeContainer */ //region
    override fun initHomePageSubcomponent() =
        appComponent.getHomePageSubcomponent().also {
            homePageSubcomponent = it
        }

    override fun destroyHomePageSubcomponent() {
        homePageSubcomponent = null
    }
    //endregion

    companion object {
        private var _instance: App? = null
        val instance
            get() = _instance!!
    }
}