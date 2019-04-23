package com.kailin.bus.data.bus.route

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.kailin.bus.util.GsonUtil
import java.util.*

class BusSubRouteConverters {

    @TypeConverter
    fun toBusSubRoutes(value: String): ArrayList<BusSubRoute>? {
        val type = object : TypeToken<ArrayList<BusSubRoute>>() {
        }.type
        return GsonUtil.instance.gson.fromJson<ArrayList<BusSubRoute>>(value, type)
    }

    @TypeConverter
    fun toString(value: ArrayList<BusSubRoute>): String {
        return GsonUtil.instance.gson.toJson(value)
    }
}
