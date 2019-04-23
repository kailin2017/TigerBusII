package com.kailin.bus.ui.main.fragment

import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.kailin.architecture_model.architecture.ArchitectureFragment
import com.kailin.bus.R
import com.kailin.bus.databinding.MainBikeFragmentBinding

class BikeFragment : ArchitectureFragment<MainBikeFragmentBinding, BikeViewModel>() {

    private var mapView: MapView? = null

    override val layoutRes: Int
        get() = R.layout.main_bike_fragment
    override val viewModelClass: Class<BikeViewModel>
        get() = BikeViewModel::class.java

    override fun initBindings() {
        mapView = binding.mapView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mapView!!.onCreate(savedInstanceState)
        mapView!!.getMapAsync { this.onMapReady(it) }
    }

    override fun onStart() {
        super.onStart()
        mapView!!.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView!!.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView!!.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView!!.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mapView!!.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView!!.onLowMemory()
    }

    private fun onMapReady(googleMap: GoogleMap) {
        val sydney = LatLng(25.0205865, 121.4208761)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}
