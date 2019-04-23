package com.kailin.bus.data.bus

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.kailin.bus.util.GsonUtil

class BusCityConverters {

    @TypeConverter
    fun toRouteOperator(value: String): BusCity? {
        return BusCity.valueOf(value)
    }

    @TypeConverter
    fun toString(value: BusCity): String {
        return value.name
    }
}
