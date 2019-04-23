package com.kailin.bus.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder

import java.util.concurrent.atomic.AtomicReference

class GsonUtil private constructor() {

    val gson: Gson = GsonBuilder()
            .setLenient()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssX")
            .create()

    companion object {

        private val reference = AtomicReference<GsonUtil>()

        val instance: GsonUtil
            get() {
                while (true) {
                    var instance: GsonUtil? = reference.get()
                    if (instance != null)
                        return instance

                    instance = GsonUtil()
                    if (reference.compareAndSet(null, instance))
                        return instance
                }
            }
    }

}
