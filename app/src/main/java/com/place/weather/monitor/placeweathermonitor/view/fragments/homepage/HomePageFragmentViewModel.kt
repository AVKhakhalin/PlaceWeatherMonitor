package com.place.weather.monitor.placeweathermonitor.view.fragments.homepage

import androidx.lifecycle.LiveData
import com.place.weather.monitor.placeweathermonitor.model.base.BaseViewModel
import com.place.weather.monitor.placeweathermonitor.model.data.AppState
import com.place.weather.monitor.placeweathermonitor.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomePageFragmentViewModel @Inject constructor (
    private val weatherRepository: WeatherRepository
): BaseViewModel<AppState>() {
    private val liveDataForViewToObserve: LiveData<AppState> = _mutableLiveData
    fun getLastKnownWeatherData(isOnline: Boolean, latitude: Double, longitude: Double) {
        _mutableLiveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch {
            withContext(Dispatchers.IO) {
                _mutableLiveData.postValue(
                    AppState.Success(
                        weatherRepository.getAllLastData(
                            isOnline = isOnline,
                            latitude = latitude,
                            longitude = longitude,
                        )
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