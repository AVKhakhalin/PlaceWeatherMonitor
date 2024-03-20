package com.place.weather.monitor.placeweathermonitor.view.fragments.homepage

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.*
import com.place.weather.monitor.placeweathermonitor.R
import com.place.weather.monitor.placeweathermonitor.databinding.FragmentHomePageBinding
import com.place.weather.monitor.placeweathermonitor.view.model.base.BaseFragment
import java.util.concurrent.atomic.AtomicInteger

class HomePageFragment : BaseFragment<FragmentHomePageBinding>
    (FragmentHomePageBinding::inflate) {
    //regin Исходные данные
        // Разрешения для работы с геолокацией
    companion object {
        private const val TAG = "GEO_DATA_INV"
        private const val PERMISSION_REQUESTS = 44
        private val REQUIRED_RUNTIME_PERMISSIONS =
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
    }
        // FusedLocationProviderClient для получения геокоординат
    lateinit var mFusedLocationClient: FusedLocationProviderClient
        // Фильтр для одиночного получения геоданных
    private var numberGeoDatesFilter: AtomicInteger = AtomicInteger(0)
    //endregion

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Настройка кнопки перехода на фрагмент с детальной информацией
        binding.buttonDetailWeatherFragment
            .setOnClickListener {
            this.findNavController()
                .navigate(R.id.action_home_page_fragment_to_detail_weather_fragment, arguments)
        }

        // Настройка класса mFusedLocationClient для получения геокоординат
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        // Получение необходимых разрешений для получения геокоординат
        if (!allRuntimePermissionsGranted()) {
            getRuntimePermissions()
        } else {
            lastLocation
        }
    }

    //region Получение разрешений на работу с геолокацией
    private fun allRuntimePermissionsGranted(): Boolean {
        for (permission in REQUIRED_RUNTIME_PERMISSIONS) {
            if (!isPermissionGranted(requireContext(), permission)) {
                return false
            }
        }
        return true
    }
    private fun getRuntimePermissions() {
        val permissionsToRequest = ArrayList<String>()
        for (permission in REQUIRED_RUNTIME_PERMISSIONS) {
            if (!isPermissionGranted(requireContext(), permission)) {
                permissionsToRequest.add(permission)
            }
        }
        if (permissionsToRequest.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                permissionsToRequest.toTypedArray(),
                PERMISSION_REQUESTS
            )
        }
    }
    private fun isPermissionGranted(context: Context, permission: String): Boolean {
        if (ContextCompat.checkSelfPermission(context, permission) ==
            PackageManager.PERMISSION_GRANTED
        ) {
            // Запуск метода получения геокоординат
            lastLocation
            return true
        }
        Toast.makeText(requireContext(),
            requireContext().resources.getString(R.string.error_permissions_geodata_text),
            Toast.LENGTH_SHORT).show()
        return false
    }
    //endregion

    //region Методы для получения геокоординат
    private val lastLocation: Unit
        get() {
            // Проверка, включена ли геолокация
            if (isLocationEnabled) {
                if (ActivityCompat.checkSelfPermission(requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.ACCESS_COARSE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED
                ) { return }
                mFusedLocationClient.lastLocation
                    .addOnCompleteListener { task ->
                        val location: Location? = task.result
                        if ((location == null) && (allRuntimePermissionsGranted())) {
                            requestNewLocationData()
                        } else {
                            // Возврат результата с геокоординатами
                            if (numberGeoDatesFilter.get() == 0) {
                                location?.let {
                                    Log.d(TAG, "${it.latitude}, ${it.longitude}")
                                }
                                numberGeoDatesFilter.set(1)
                            }
                        }
                    }
            } else {
                Toast.makeText(requireContext(),
                    requireContext().resources.getString(R.string.error_geolocation_needed_text),
                    Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        }
        // Получение новых геоданных
    private fun requestNewLocationData() {
        val mLocationRequest = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval(5)
            .setFastestInterval(0)
            .setNumUpdates(1)
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        if (ActivityCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) { return }
        mFusedLocationClient.requestLocationUpdates(
            mLocationRequest,
            mLocationCallback,
            Looper.myLooper()
        )
    }
        // Возврат результата с геокоординатами
    private val mLocationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val mLastLocation: Location = locationResult.lastLocation
            if (numberGeoDatesFilter.get() == 0) {
                Log.d(TAG, "${mLastLocation.latitude}, ${mLastLocation.longitude}")
                numberGeoDatesFilter.set(1)
            }
        }
    }
        // Метод для проверки включена ли геолокация или нет
    private val isLocationEnabled: Boolean
        get() {
            val locationManager =
                requireActivity().getSystemService(AppCompatActivity.LOCATION_SERVICE) as
                LocationManager
            return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                    locationManager.isProviderEnabled(
                        LocationManager.NETWORK_PROVIDER)
        }
    override fun onResume() {
        super.onResume()
        if (!allRuntimePermissionsGranted()) {
            getRuntimePermissions()
        } else {
            lastLocation
        }
    }
    //endregion
}