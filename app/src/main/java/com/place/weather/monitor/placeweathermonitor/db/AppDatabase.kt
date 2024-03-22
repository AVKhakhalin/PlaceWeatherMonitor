package com.place.weather.monitor.placeweathermonitor.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.place.weather.monitor.placeweathermonitor.app.App
import com.place.weather.monitor.placeweathermonitor.db.converters.WeatherDataConverters
import com.place.weather.monitor.placeweathermonitor.db.dao.WeatherDataDao
import com.place.weather.monitor.placeweathermonitor.db.entity.WeatherDataEntity
import com.place.weather.monitor.placeweathermonitor.utils.DATABASE_FILE_NAME

@Database(
    entities = [
        WeatherDataEntity::class,
    ],
    version = 1
)
@TypeConverters(WeatherDataConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract val weatherDataDao: WeatherDataDao

    companion object {
        val instance by lazy {
            Room.databaseBuilder(App.instance, AppDatabase::class.java, DATABASE_FILE_NAME)
                .build()
        }
    }
}