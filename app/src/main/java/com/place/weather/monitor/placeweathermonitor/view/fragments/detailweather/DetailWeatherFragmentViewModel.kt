package com.place.weather.monitor.placeweathermonitor.view.fragments.detailweather

import androidx.lifecycle.LiveData
import com.place.weather.monitor.placeweathermonitor.model.base.BaseViewModel
import com.place.weather.monitor.placeweathermonitor.model.data.AppState
import com.place.weather.monitor.placeweathermonitor.repository.cache.WeatherDataCache
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class DetailWeatherFragmentViewModel @Inject constructor(
    private val weatherDataCache: WeatherDataCache
): BaseViewModel<AppState>() {
    //region Исходные данные
    private val liveDataForViewToObserve: LiveData<AppState> = _mutableLiveData
    //endregion

    fun getDetailWeatherData(dateLong: Long) {
        _mutableLiveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch {
            withContext(Dispatchers.IO) {
                _mutableLiveData.postValue(
                    AppState.SuccessGetDetailedWeatherData(
                        weatherDataCache.getDataByDate(Date(dateLong))
                    )
                )
            }
        }
    }

    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(AppState.Error(error))
    }

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }
}