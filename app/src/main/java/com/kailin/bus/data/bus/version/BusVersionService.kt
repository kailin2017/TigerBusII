package com.kailin.bus.data.bus.version

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface BusVersionService {

    @GET("/MOTC/v2/Bus/DataVersion/City/{city}?\$format=JSON")
    fun getBusVersion(@Path("city") city: String): Observable<BusVersion>
}
