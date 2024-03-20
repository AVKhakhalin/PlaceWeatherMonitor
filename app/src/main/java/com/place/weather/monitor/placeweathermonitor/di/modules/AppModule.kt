package com.place.weather.monitor.placeweathermonitor.di.modules

import android.content.Context
import com.place.weather.monitor.placeweathermonitor.app.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {

    @Singleton
    @Provides
    fun context(): Context {
        return app
    }

    @Singleton
    @Provides
    fun app(): App {
        return app
    }
}