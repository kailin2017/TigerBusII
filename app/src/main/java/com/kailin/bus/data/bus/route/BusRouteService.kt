package com.kailin.bus.data.bus.route

import java.util.ArrayList

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface BusRouteService {

    @GET("/MOTC/v2/Bus/Route/City/{city}?\$format=JSON")
    fun getBusRoute(@Path("city") city: String): Observable<ArrayList<BusRoute>>
}
