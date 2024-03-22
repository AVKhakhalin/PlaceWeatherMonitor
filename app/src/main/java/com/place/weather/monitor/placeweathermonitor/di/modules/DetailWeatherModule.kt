package com.place.weather.monitor.placeweathermonitor.di.modules

import com.place.weather.monitor.placeweathermonitor.app.App
import com.place.weather.monitor.placeweathermonitor.db.AppDatabase
import com.place.weather.monitor.placeweathermonitor.di.scope.DetailWeatherScope
import com.place.weather.monitor.placeweathermonitor.di.scope.containers.DetailWeatherScopeContainer
import com.place.weather.monitor.placeweathermonitor.repository.cache.WeatherDataCache
import com.place.weather.monitor.placeweathermonitor.repository.cache.WeatherDataCacheImpl
import dagger.Module
import dagger.Provides

@Module
abstract class DetailWeatherModule {

    companion object {
        @DetailWeatherScope
        @Provides
        fun reposCache(
            db: AppDatabase,
        ): WeatherDataCache {
            return WeatherDataCacheImpl(db)
        }

        @DetailWeatherScope
        @Provides
        fun scopeContainer(app: App): DetailWeatherScopeContainer = app
    }
}