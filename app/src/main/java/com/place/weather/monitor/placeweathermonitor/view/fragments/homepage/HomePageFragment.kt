package com.place.weather.monitor.placeweathermonitor.view.fragments.homepage

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.place.weather.monitor.placeweathermonitor.R
import com.place.weather.monitor.placeweathermonitor.databinding.FragmentHomePageBinding
import com.place.weather.monitor.placeweathermonitor.view.model.base.BaseFragment

class HomePageFragment : BaseFragment<FragmentHomePageBinding>
    (FragmentHomePageBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Inflate the layout for this fragment
        binding.buttonDetailWeatherFragment
            .setOnClickListener {
            this.findNavController()
                .navigate(R.id.action_home_page_fragment_to_detail_weather_fragment, arguments)
        }
    }
}