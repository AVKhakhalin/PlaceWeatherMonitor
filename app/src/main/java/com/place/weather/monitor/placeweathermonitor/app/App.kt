package com.place.weather.monitor.placeweathermonitor.app

import android.app.Application
import com.place.weather.monitor.placeweathermonitor.di.components.AppComponent
import com.place.weather.monitor.placeweathermonitor.di.components.DaggerAppComponent
import com.place.weather.monitor.placeweathermonitor.di.modules.AppModule

class App: Application() {
    /** Исходные данные */ //region
    // appComponent
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
    //endregion

    override fun onCreate() {
        super.onCreate()
        _instance = this
    }

    companion object {
        private var _instance: App? = null
        val instance
            get() = _instance!!
    }
}