package com.place.weather.monitor.placeweathermonitor.view.fragments.detailweather

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.place.weather.monitor.placeweathermonitor.R
import com.place.weather.monitor.placeweathermonitor.app.App
import com.place.weather.monitor.placeweathermonitor.databinding.FragmentDetailWeatherBinding
import com.place.weather.monitor.placeweathermonitor.model.base.BaseFragment
import com.place.weather.monitor.placeweathermonitor.model.data.AppState
import com.place.weather.monitor.placeweathermonitor.utils.ERROR_TAG
import com.place.weather.monitor.placeweathermonitor.utils.functions.createDetailWeatherReport
import java.util.*
import javax.inject.Inject

class DetailWeatherFragment : BaseFragment<FragmentDetailWeatherBinding>
    (FragmentDetailWeatherBinding::inflate) {
    //region Исходные данные
        // Дата для получения детальной информации
    private var dateForDetailWeatherInfoLong: Long = 0
        // ViewModel
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: DetailWeatherFragmentViewModel by viewModels {
        factory
    }
        // Инстанс фрагмента для получения исходных данных
    companion object {
        private const val TAG = "DETAIL_WEATHER_INFO"
        private const val CURRENT_DATE_LONG_TAG: String = "CURRENT_DATE_LONG_TAG"
        fun newInstance(dateForDetailWeatherInfoLong: Long) = DetailWeatherFragment().apply {
            arguments = Bundle().apply {
                putLong(CURRENT_DATE_LONG_TAG, dateForDetailWeatherInfoLong)
            }
        }
    }
    //endregion

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            dateForDetailWeatherInfoLong = it.getLong(CURRENT_DATE_LONG_TAG)
        }
        // Инициализация ViewModel
        initViewModel()
    }

    // Установка ViewModel
    fun initViewModel() {
        this.viewModel.subscribe().observe(viewLifecycleOwner) {
            renderData(it)
        }
        viewModel.getDetailWeatherData(dateForDetailWeatherInfoLong)
    }

    // Отображение получаемой информации от ViewModel
    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.SuccessGetDetailedWeatherData -> {
                appState.inputData?.let {
                    binding.weatherDetailedDataText.text = it.createDetailWeatherReport()
                }
                binding.progressBarWeatherDataShortInfo.visibility = View.GONE
            }
            is AppState.Loading -> {
                binding.progressBarWeatherDataShortInfo.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                Log.d(ERROR_TAG, "${appState.error.message}")
            }
            else -> {
                Log.d(
                    ERROR_TAG, requireContext().resources
                    .getString(R.string.error_get_data_not_for_current_window_text))
            }
        }
    }

    //region Настрока Scope для фрагмента
    override fun onAttach(context: Context) {
        App.instance.appComponent.injectDetailWeatherFragment(this)
        super.onAttach(context)
    }
}