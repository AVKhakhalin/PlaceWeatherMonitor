package com.place.weather.monitor.placeweathermonitor.view.fragments.detailweather

import androidx.lifecycle.LiveData
import com.place.weather.monitor.placeweathermonitor.model.base.BaseViewModel
import com.place.weather.monitor.placeweathermonitor.model.data.AppState
import com.place.weather.monitor.placeweathermonitor.repository.cache.WeatherDataCache
import javax.inject.Inject

class DetailWeatherFragmentViewModel @Inject constructor(
    private val weatherDataCache: WeatherDataCache
): BaseViewModel<AppState>() {
    private val liveDataForViewToObserve: LiveData<AppState> = _mutableLiveData

    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(AppState.Error(error))
    }

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }
}