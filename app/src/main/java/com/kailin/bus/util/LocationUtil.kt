package com.kailin.bus.util

import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationManager
import java.util.concurrent.atomic.AtomicReference
import android.location.Criteria
import android.location.LocationListener
import android.os.Looper


class LocationUtil(val context: Context) {

    private val locationManager = context.applicationContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    @SuppressLint("MissingPermission")
    fun requestSingleUpdate(listener: LocationListener) {
        locationManager.requestSingleUpdate(getBestProvider(), listener, Looper.getMainLooper())
    }

    @SuppressLint("MissingPermission")
    fun requestUpdate(listener: LocationListener) {
        locationManager.requestLocationUpdates(getBestProvider(), 5, 100f, listener)
    }

    fun removeUpdate(listener: LocationListener) {
        locationManager.removeUpdates(listener)
    }

    private fun getBestProvider(): String {
        return locationManager.getBestProvider(Criteria(), true)
    }

    companion object {

        private val reference = AtomicReference<LocationUtil>()

         fun getInstance(context: Context): LocationUtil {
            while (true) {
                var instance = reference.get()
                if (instance != null)
                    return instance

                instance = LocationUtil(context)
                if (reference.compareAndSet(null, instance))
                    return instance
            }
        }
    }
}
