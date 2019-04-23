package com.kailin.bus

import android.app.Application

import com.kailin.bus.data.bus.BusDatabase

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        BusDatabase.createInstance(this)
    }

    companion object {

         var instance: MyApplication? = null
             private set
    }
}
