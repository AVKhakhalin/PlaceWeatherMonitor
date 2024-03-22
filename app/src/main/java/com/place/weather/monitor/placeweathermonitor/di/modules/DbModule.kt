package com.place.weather.monitor.placeweathermonitor.di.modules

import android.content.Context
import androidx.room.Room
import com.place.weather.monitor.placeweathermonitor.db.AppDatabase
import com.place.weather.monitor.placeweathermonitor.utils.DATABASE_FILE_NAME
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {
    @Singleton
    @Provides
    fun db(context: Context): AppDatabase =
        Room
            .databaseBuilder(context, AppDatabase::class.java, DATABASE_FILE_NAME)
            .build()
}