package com.place.weather.monitor.placeweathermonitor.view.fragments.homepage.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.place.weather.monitor.placeweathermonitor.R
import com.place.weather.monitor.placeweathermonitor.model.core.WeatherDataWithDateShortInfo
import com.place.weather.monitor.placeweathermonitor.utils.DATE_FORMAT
import com.place.weather.monitor.placeweathermonitor.utils.functions.convertToColor
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class WeatherDataShortInfoRecyclerAdapter(
    private val callbackWeatherDataChoosed: CallbackWeatherDataChoosed,
) : RecyclerView.Adapter<WeatherDataShortInfoRecyclerAdapter.MyViewHolder>() {
    /** Исходные данные */ //region
    private var weatherDataList: List<WeatherDataWithDateShortInfo> = listOf()
    //endregion

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val weatherDataContainer: ConstraintLayout =
            itemView.findViewById(R.id.weather_info_container)
        val weatherDate: TextView = itemView.findViewById(R.id.date_text)
        val weatherPlace: TextView = itemView.findViewById(R.id.place_text)
        val weatherHumidityPercent: TextView = itemView.findViewById(R.id.humidity_percent_value)
        val weatherHumidityPercentCircle: CircularProgressIndicator =
            itemView.findViewById(R.id.humidity_percent_circle)
        val weatherCloudPercent: TextView = itemView.findViewById(R.id.cloudy_percent_value)
        val weatherCloudPercentCircle: CircularProgressIndicator =
            itemView.findViewById(R.id.cloudy_percent_circle)
        val weatherWind: TextView = itemView.findViewById(R.id.wind_value)
        val weatherTemperatureMin: TextView = itemView.findViewById(R.id.temperature_min_value)
        val weatherTemperatureMax: TextView = itemView.findViewById(R.id.temperature_max_value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_home_page_item, parent, false)
        return MyViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // Отображение данных
        holder.weatherDate.text = SimpleDateFormat(
            DATE_FORMAT,
            Locale.getDefault()
        ).format(weatherDataList[position].date)
        holder.weatherPlace.text = weatherDataList[position].name
        holder.weatherHumidityPercent.text = "${weatherDataList[position].humidity}"
        holder.weatherHumidityPercentCircle.progress = weatherDataList[position].humidity
        holder.weatherHumidityPercentCircle.setIndicatorColor(
            weatherDataList[position].humidity.convertToColor()
        )
        holder.weatherCloudPercent.text = "${weatherDataList[position].all}"
        holder.weatherCloudPercentCircle.setIndicatorColor(
            weatherDataList[position].all.convertToColor()
        )
        holder.weatherWind.text = "${weatherDataList[position].speed.roundToInt()} м/с"
        holder.weatherTemperatureMin.text = "от ${weatherDataList[position].temp_min.roundToInt()}"
        holder.weatherTemperatureMax.text = "до ${weatherDataList[position].temp_max.roundToInt()}"

        // Установка события нажатия на контейнер с погодными данными
        holder.weatherDataContainer.setOnClickListener {
            callbackWeatherDataChoosed.sendChoosedWeatherDate(weatherDataList[position].date)
        }
    }

    override fun getItemCount() = weatherDataList.size

    //region Методы для корректного возврата номера элемента списка даже после пересоздания
    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    //endregion

    //region МЕТОД И КЛАСС ДЛЯ ДИНАМИЧЕСКОГО ОБНОВЛЕНИЯ СПИСКА
    fun submitList(newListData: List<WeatherDataWithDateShortInfo>) {
        val oldListData: List<WeatherDataWithDateShortInfo> = weatherDataList
        val diffResult: DiffUtil.DiffResult =
            DiffUtil.calculateDiff(DiffCallback(oldListData, newListData), true)
        weatherDataList = newListData
        diffResult.dispatchUpdatesTo(this)
    }

    class DiffCallback(
        var oldList: List<WeatherDataWithDateShortInfo>,
        var newList: List<WeatherDataWithDateShortInfo>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].equals(newList[newItemPosition])
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].equals(newList[newItemPosition])
        }
    }
    //endregion
}