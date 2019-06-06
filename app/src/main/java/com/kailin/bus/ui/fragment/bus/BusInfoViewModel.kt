package com.kailin.bus.ui.fragment.bus

import androidx.annotation.NonNull
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.kailin.architecture_model.architecture.ArchitectureViewModel
import com.kailin.bus.connect.RetrofitManager
import com.kailin.bus.connect.RetrofitService
import com.kailin.bus.data.bus.display.BusDiaplayStopOfRouteService
import com.kailin.bus.data.bus.display.BusDisplayStopOfRoute
import com.kailin.bus.data.bus.estimate.BusN1EstimateTime
import com.kailin.bus.data.bus.estimate.BusN1EstimateTimeService
import com.kailin.bus.data.bus.route.BusRoute
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.functions.Consumer
import io.reactivex.rxkotlin.Observables
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class BusInfoViewModel : ArchitectureViewModel() {

    private val retrofitManager = RetrofitManager.getInstance(RetrofitService.PTX_L1)
    private val busDiaplayStopOfRouteService = retrofitManager.create(BusDiaplayStopOfRouteService::class.java)
    private val busDisplayStopOfRouteData = MutableLiveData<List<BusDisplayStopOfRoute>>()
    private val busN1EstimateTimeService = retrofitManager.create(BusN1EstimateTimeService::class.java)
    private val busN1EstimateTimeData = MutableLiveData<List<BusN1EstimateTime>>()

    fun observe(@NonNull busRoute: BusRoute,
                @NonNull owner: LifecycleOwner,
                @NonNull observer1: Observer<List<BusDisplayStopOfRoute>>,
                @NonNull observer2: Observer<List<BusN1EstimateTime>>) {

        busDisplayStopOfRouteData.observe(owner, observer1)
        busN1EstimateTimeData.observe(owner, observer2)

        val observable1 = busDiaplayStopOfRouteService
                .getBusDiaplayStopOfRoute(busRoute.city!!.name, busRoute.routeName!!.zh_tw!!)

        rxOptimization(observable1, Consumer { busDisplayStopOfRouteData.postValue(it) })


        val observable2 = Observable
                .interval(15, TimeUnit.SECONDS, Schedulers.io())
                .flatMap { busN1EstimateTimeService.getEstimateTime(busRoute.city!!.name, busRoute.routeName!!.zh_tw!!) }

        rxOptimization(observable2, Consumer { busN1EstimateTimeData.postValue(it)  })
    }
}