package com.place.weather.monitor.placeweathermonitor.utils.network

import android.annotation.SuppressLint
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class BaseInterceptor: Interceptor {
    /* Исходные данные */ //region
    companion object {
        private const val TAG = "INTERCEPTOR_INV"
    }
    //endregion

    @SuppressLint("SimpleDateFormat")
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        Log.d(TAG, "Запрос: ${response.request}")
        Log.d(TAG, "Ответ: ${response.networkResponse}")
        Log.d(TAG, "Заголовок ответа: ${response.headers}")
        Log.d(TAG, "Код результата запроса: ${getResponseCode(response.code)}")
        Log.d(TAG, "Body результата запроса: ${response.body.toString()}")
        return response
    }

    fun getResponseCode(responseCode: Int): ServerResponseStatusCode {
        var statusCode = ServerResponseStatusCode.UNDEFINED_ERROR
        when (responseCode / 100) {
            1 -> statusCode = ServerResponseStatusCode.INFO
            2 -> statusCode = ServerResponseStatusCode.SUCCESS
            3 -> statusCode = ServerResponseStatusCode.REDIRECTION
            4 -> statusCode = ServerResponseStatusCode.CLIENT_ERROR
            5 -> statusCode = ServerResponseStatusCode.SERVER_ERROR
        }
        return statusCode
    }

    // Типы сообщений при обмене сообщениями с сервером
    enum class ServerResponseStatusCode {
        INFO,
        SUCCESS,
        REDIRECTION,
        CLIENT_ERROR,
        SERVER_ERROR,
        UNDEFINED_ERROR
    }
}