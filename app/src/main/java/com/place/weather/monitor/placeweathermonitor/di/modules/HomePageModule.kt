package com.place.weather.monitor.placeweathermonitor.di.modules

import com.place.weather.monitor.placeweathermonitor.app.App
import com.place.weather.monitor.placeweathermonitor.db.AppDatabase
import com.place.weather.monitor.placeweathermonitor.di.scope.HomePageScope
import com.place.weather.monitor.placeweathermonitor.di.scope.containers.HomePageScopeContainer
import com.place.weather.monitor.placeweathermonitor.repository.WeatherRepository
import com.place.weather.monitor.placeweathermonitor.repository.WeatherRepositoryImpl
import com.place.weather.monitor.placeweathermonitor.repository.cache.WeatherDataCache
import com.place.weather.monitor.placeweathermonitor.repository.cache.WeatherDataCacheImpl
import com.place.weather.monitor.placeweathermonitor.repository.retrofit.WeatherDataRetrofit
import com.place.weather.monitor.placeweathermonitor.repository.retrofit.WeatherDataRetrofitImpl
import com.place.weather.monitor.placeweathermonitor.api.RetrofitService
import dagger.Module
import dagger.Provides

@Module
abstract class HomePageModule {

    companion object {
        @HomePageScope
        @Provides
        fun reposRepo(
            weatherDataRetrofit: WeatherDataRetrofit,
            weatherDataCache: WeatherDataCache,
        ): WeatherRepository {
            return WeatherRepositoryImpl(
                weatherDataRetrofit = weatherDataRetrofit,
                weatherDataCache = weatherDataCache)
        }

        @HomePageScope
        @Provides
        fun reposRetrofit(
            retrofitService: RetrofitService,
        ): WeatherDataRetrofit {
            return WeatherDataRetrofitImpl(retrofitService)
        }

        @HomePageScope
        @Provides
        fun reposCache(
            db: AppDatabase,
        ): WeatherDataCache {
            return WeatherDataCacheImpl(db)
        }

        @HomePageScope
        @Provides
        fun scopeContainer(app: App): HomePageScopeContainer = app
    }
}