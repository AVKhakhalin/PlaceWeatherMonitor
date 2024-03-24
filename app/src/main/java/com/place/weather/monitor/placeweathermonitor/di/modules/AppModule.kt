package com.place.weather.monitor.placeweathermonitor.di.modules

import android.content.Context
import com.place.weather.monitor.placeweathermonitor.api.RetrofitService
import com.place.weather.monitor.placeweathermonitor.app.App
import com.place.weather.monitor.placeweathermonitor.db.AppDatabase
import com.place.weather.monitor.placeweathermonitor.repository.WeatherRepository
import com.place.weather.monitor.placeweathermonitor.repository.WeatherRepositoryImpl
import com.place.weather.monitor.placeweathermonitor.repository.cache.WeatherDataCache
import com.place.weather.monitor.placeweathermonitor.repository.cache.WeatherDataCacheImpl
import com.place.weather.monitor.placeweathermonitor.repository.retrofit.WeatherDataRetrofit
import com.place.weather.monitor.placeweathermonitor.repository.retrofit.WeatherDataRetrofitImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [
        ViewModelModule::class,
    ]
)
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

    @Singleton
    @Provides
    fun reposRepo(
        weatherDataRetrofit: WeatherDataRetrofit,
        weatherDataCache: WeatherDataCache,
    ): WeatherRepository {
        return WeatherRepositoryImpl(
            weatherDataRetrofit = weatherDataRetrofit,
            weatherDataCache = weatherDataCache
        )
    }

    @Singleton
    @Provides
    fun reposRetrofit(
        retrofitService: RetrofitService,
    ): WeatherDataRetrofit {
        return WeatherDataRetrofitImpl(retrofitService)
    }

    @Singleton
    @Provides
    fun reposCache(
        db: AppDatabase,
    ): WeatherDataCache {
        return WeatherDataCacheImpl(db)
    }
}