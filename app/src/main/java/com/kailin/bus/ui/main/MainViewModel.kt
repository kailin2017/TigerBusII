package com.kailin.bus.ui.main

import com.kailin.bus.MyApplication
import com.kailin.bus.base.BaseViewModel
import com.kailin.bus.connect.RetrofitManager
import com.kailin.bus.connect.RetrofitService
import com.kailin.bus.data.bus.BusCity
import com.kailin.bus.data.bus.BusDatabase
import com.kailin.bus.data.bus.route.BusRoute
import com.kailin.bus.data.bus.route.BusRouteService
import com.kailin.bus.data.bus.version.BusVersion
import com.kailin.bus.data.bus.version.BusVersionService
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Consumer
import io.reactivex.rxkotlin.Observables
import java.util.*

class MainViewModel : BaseViewModel() {

    private val retrofitManager = RetrofitManager.getInstance(RetrofitService.PTX_L1)
    private val busVersionService = retrofitManager.create(BusVersionService::class.java)
    private val busRouteService = retrofitManager.create(BusRouteService::class.java)
    private val busDatabase = MyApplication.instance?.let { BusDatabase.getInstance(it) }
    private val busRouteDao = busDatabase!!.busRouteDao()


    override fun onDestroy() {
        busDatabase!!.close()
    }

    fun getBusVersion() {
        val observable = Observable.fromArray(*BusCity.values())
                .flatMap<String> { this.cityFlatMap(it) }
                .filter { this.busVersionFilter(it) }
                .flatMap<ArrayList<BusRoute>> { this.busRouteFlatMap(it) }

        rxOptimization(observable, Consumer { busRouteOnNext(it) })
    }

    private fun cityFlatMap(busCity: BusCity): Observable<String> {
        val busCityName = busCity.name
        return Observable.zip(
                busVersionService.getBusVersion(busCityName),
                Observable.just(busCityName),
                BiFunction<BusVersion, String, String> { busVersion, city -> this.busVersionBiFunction(busVersion, city) })
    }

    private fun busVersionBiFunction(busVersion: BusVersion, city: String): String {
        busVersion.busCity = city
        val busVersionLocal = busDatabase!!.busVersionDao().getBusVersion(city)
        var isUpdateBusRoute = false
        when {
            busVersionLocal == null -> // 無local版本紀錄
                isUpdateBusRoute = true
            busVersionLocal.compareTo(busVersion) == -1 -> // local資料較舊
                isUpdateBusRoute = true
            busRouteDao.busRouteCount < 1 -> // local無路線資料
                isUpdateBusRoute = true
            busRouteDao.getOldBusRoute(busVersion.versionID) > 0 -> // local有舊版本資料
                isUpdateBusRoute = true
        }
        return if (isUpdateBusRoute) city else ""
    }

    private fun busVersionFilter(city: String): Boolean {
        return city.isNotEmpty()
    }

    private fun busRouteFlatMap(city: String): Observable<ArrayList<BusRoute>> {
        return busRouteService.getBusRoute(city)
    }

    private fun busRouteOnNext(busRoutes: ArrayList<BusRoute>) {
        busRouteDao.insert(busRoutes as List<BusRoute>)
        busRouteDao.busRoutes
    }
}
