package com.kdw.newsapp.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

@Suppress("DEPRECATION")
object NetworkCheck {
    fun isNetworkConnected(context: Context): Boolean {

        val networkManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val networkCapabilities = networkManager.getNetworkCapabilities(networkManager.activeNetwork)

            if(networkCapabilities != null) {
                if(networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI))
                    return true
                else if(networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
                    return true
                else if(networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET))
                    return true
            }
        } else {
            val activeNetwork = networkManager.activeNetworkInfo
            if(activeNetwork != null && activeNetwork.isConnected) {
                return true
            }
        }

        return false
    }
}