package com.kailin.bus.data.bus.display

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface BusDiaplayStopOfRouteService {

    @GET("/MOTC/v2/Bus/DisplayStopOfRoute/City/{city}/{routeName}?\$format=JSON")
    fun getBusDiaplayStopOfRoute(@Path("city") city: String, @Path("routeName") routeName: String): Observable<List<BusDisplayStopOfRoute>>
}