package com.kailin.bus.data.bus.estimate

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface BusN1EstimateTimeService {

    @GET("/MOTC/v2/Bus/EstimatedTimeOfArrival/City/{city}/{routeName}?\$format=JSON")
    fun getEstimateTime(@Path("city") city: String, @Path("routeName") routeName: String): Observable<List<BusN1EstimateTime>>
}