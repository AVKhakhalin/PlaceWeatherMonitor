package com.place.weather.monitor.placeweathermonitor.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.place.weather.monitor.placeweathermonitor.model.base.ViewModelFactory
import com.place.weather.monitor.placeweathermonitor.view.fragments.detailweather.DetailWeatherFragmentViewModel
import com.place.weather.monitor.placeweathermonitor.view.fragments.homepage.HomePageFragmentViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomePageFragmentViewModel::class)
    abstract fun bindHomePageFragmentViewModel(
        imagesListViewModel: HomePageFragmentViewModel
    ): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailWeatherFragmentViewModel::class)
    abstract fun bindDetailWeatherFragmentViewModel(
        singleImageViewModel: DetailWeatherFragmentViewModel
    ): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}

@MustBeDocumented
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)