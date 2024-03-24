package com.place.weather.monitor.placeweathermonitor.utils

// Количество дней, за которые нужно получать из базы данных погодные данные
const val NUMBER_LAST_DAYS: Int = 5
// Имя файла с базой данных
const val DATABASE_FILE_NAME: String = "database.db"
// Базовая ссылка API
const val API_BASE_URL: String = "https://api.openweathermap.org/data/2.5/"
// Язык предоставления данных о погоде
const val API_LANGUAGE: String = "ru"
// Единицы измерения предоставленых данных о погоде
const val API_UNITS: String = "metric"
// Код ошибки при получении погодных данных
const val ERROR_CODE: Int = -1
// Формат используемой даты
const val DATE_FORMAT: String = "yyyy-MM-dd"
// Тег для ошибочных логов
const val ERROR_TAG: String = "ERROR_INV"
