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
// Тег для ошибочных логов
const val ERROR_TAG: String = "ERROR_INV"
// Формат вывода даты
const val DATE_FORMAT: String = "dd-MM-yyyy"
// Константа для отображения цвета на круговом индикаторе (отвечает за прозрачность)
const val ALPHA_NOT_OPAQUE: Int = 255
// Код успешного получения данных из сети
const val SUCCESS_GET_DATA: Int = 200