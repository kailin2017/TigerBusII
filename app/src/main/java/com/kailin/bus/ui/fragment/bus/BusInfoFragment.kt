package com.kailin.bus.ui.fragment.bus

import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.tabs.TabLayout
import com.kailin.architecture_model.architecture.ArchitectureFragment
import com.kailin.bus.R
import com.kailin.bus.data.bus.route.BusRoute
import com.kailin.bus.databinding.BusInfoFragmentBinding
import com.kailin.bus.util.LocationUtil
import com.kailin.bus.util.OnConfigureTabCallback
import com.kailin.bus.util.TabPager2Mediator

class BusInfoFragment : ArchitectureFragment<BusInfoFragmentBinding, BusInfoViewModel>(), LocationListener {

    private lateinit var locationUtil: LocationUtil
    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>
    private val busInfoAdapters = ArrayList<BusInfoRecyclerAdapter>()
    private lateinit var busInfoPager2Adapter  : BusInfoPager2Adapter

    override val layoutRes: Int
        get() = R.layout.bus_info_fragment
    override val viewModelClass: Class<BusInfoViewModel>
        get() = BusInfoViewModel::class.java

    override fun initBindings() {
        mapView = binding.mapView
        locationUtil = LocationUtil.getInstance(context!!)
        bottomSheetBehavior = BottomSheetBehavior.from(binding.sheet.bottomSheetLayout)

        val busRoute = arguments!![BusRoute.BUS_ROUTE] as BusRoute

        viewModel.observe(busRoute,
                this,
                Observer {
                    if (busRoute.hasSubRoutes) {
                        for (subRoute in busRoute.subRoutes!!) {
                            val adapter = BusInfoRecyclerAdapter(it)
                            busInfoAdapters.add(adapter)
                        }
                    } else {
                        val adapter = BusInfoRecyclerAdapter(it)
                        busInfoAdapters.add(adapter)
                    }
                    busInfoPager2Adapter = BusInfoPager2Adapter(busInfoAdapters)
                    binding.sheet.viewPager2.adapter = busInfoPager2Adapter
                    TabPager2Mediator(binding.sheet.tabLayout, binding.sheet.viewPager2, object : OnConfigureTabCallback {
                        override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                            tab.text = busRoute.subRoutes?.get(position)!!.subRouteName!!.zh_tw
                        }
                    })
                },
                Observer {})
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

    override fun onLocationChanged(p0: Location) {
        moveLocation(p0.latitude, p0.longitude)
    }

    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
    }

    override fun onProviderEnabled(p0: String?) {
    }

    override fun onProviderDisabled(p0: String?) {
    }

    private fun moveLocation(latitude: Double, longitude: Double) {
        val sydney = LatLng(longitude, latitude)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}