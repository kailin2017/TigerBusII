package com.kailin.bus.util

import android.content.Context

import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class LocationUtil(context: Context) {


    private val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

}
