package com.kailin.bus.ui.main.fragment

import com.kailin.bus.MyApplication
import com.kailin.bus.data.bus.BusDatabase
import com.kailin.bus.data.bus.route.BusRoute
import com.kailin.architecture_model.architecture.ArchitectureViewModel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import io.reactivex.functions.Consumer

class BusViewModel : ArchitectureViewModel() {

    private val busRouteDao = BusDatabase.getInstance(MyApplication.instance!!).busRouteDao()
    private val busRoutesLiveData = MutableLiveData<List<BusRoute>>()

    fun busRoutesObserver(owner: LifecycleOwner, observer: Observer<List<BusRoute>>) {
        busRoutesLiveData.observe(owner, observer)
    }

    fun queryBus(routeName: String) {
        rxOptimization(busRouteDao!!.getBusRoutes(routeName), Consumer { queryBusOnNext(it) })
    }

    private fun queryBusOnNext(busRoutes: List<BusRoute>) {
        busRoutesLiveData.postValue(busRoutes)
    }
}
