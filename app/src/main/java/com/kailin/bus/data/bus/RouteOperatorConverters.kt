package com.kailin.bus.data.bus

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.kailin.bus.util.GsonUtil
import java.util.*

class RouteOperatorConverters {

    @TypeConverter
    fun revertOperator(value: String): ArrayList<RouteOperator>? {
        val type = object : TypeToken<ArrayList<RouteOperator>>() {
        }.type
        return GsonUtil.instance.gson.fromJson<ArrayList<RouteOperator>>(value, type)
    }

    @TypeConverter
    fun converterString(value: ArrayList<RouteOperator>): String {
        return GsonUtil.instance.gson.toJson(value)
    }
}
