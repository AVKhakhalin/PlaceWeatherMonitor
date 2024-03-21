package com.place.weather.monitor.placeweathermonitor.utils.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.fragment.app.Fragment

fun Fragment.isNetworkAvailable(): Boolean {
    val connectivityManager =
        (requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                return true
            }
        }
        return false
    } else @Suppress("DEPRECATION") {
        return connectivityManager.activeNetworkInfo?.isConnected == true
    }
}