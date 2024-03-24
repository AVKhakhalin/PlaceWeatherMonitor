package com.place.weather.monitor.placeweathermonitor.di.modules

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.place.weather.monitor.placeweathermonitor.api.RetrofitService
import com.place.weather.monitor.placeweathermonitor.utils.API_BASE_URL
import com.place.weather.monitor.placeweathermonitor.utils.network.BaseInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

private const val BASE_URL = "BASE_URL"

@Module
class NetworkModule {
    @Singleton
    @Provides
    @Named(BASE_URL)
    fun baseUrl(): String {
        return API_BASE_URL
    }

    @Singleton
    @Provides
    fun retrofitService(
        retrofit: Retrofit
    ): RetrofitService {
        return retrofit.create<RetrofitService>(RetrofitService::class.java)
    }

    @Singleton
    @Provides
    fun getGson(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    }

    @Singleton
    @Provides
    fun getRetrofit(
        @Named(BASE_URL) baseUrl: String,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(createOkHttpClient(BaseInterceptor()))
            .build()
    }

    @Singleton
    @Provides
    fun createOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.readTimeout(120, TimeUnit.SECONDS)
        httpClient.connectTimeout(120, TimeUnit.SECONDS)
        httpClient.addInterceptor(interceptor)
        httpClient.addInterceptor(
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        )
        httpClient.protocols(Collections.singletonList(Protocol.HTTP_1_1))
        return httpClient.build()
    }
}