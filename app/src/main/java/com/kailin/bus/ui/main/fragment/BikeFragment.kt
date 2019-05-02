package com.kailin.bus.ui.main.fragment

import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.kailin.architecture_model.architecture.ArchitectureFragment
import com.kailin.bus.R
import com.kailin.bus.databinding.MainBikeFragmentBinding
import com.kailin.bus.util.LocationUtil

class BikeFragment : ArchitectureFragment<MainBikeFragmentBinding, BikeViewModel>(), LocationListener {


    private lateinit var locationUtil: LocationUtil
    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap

    override val layoutRes: Int
        get() = R.layout.main_bike_fragment
    override val viewModelClass: Class<BikeViewModel>
        get() = BikeViewModel::class.java

    override fun initBindings() {
        locationUtil = LocationUtil.getInstance(context!!)
        mapView = binding.mapView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync { this.onMapReady(it) }
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        locationUtil.requestUpdate(this)
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        // 暫停時停止監聽位置
        locationUtil.removeUpdate(this)
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    private fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
    }

    override fun onLocationChanged(p0: Location?) {
        moveLocation(p0!!.latitude, p0.longitude)
    }

    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
    }

    override fun onProviderEnabled(p0: String?) {
    }

    override fun onProviderDisabled(p0: String?) {
    }

    fun moveLocation(latitude: Double, longitude: Double) {
        val sydney = LatLng(longitude, latitude)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}
