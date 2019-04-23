package com.kailin.bus.data.bus.route

import androidx.room.TypeConverter

class BusRouteTypeConverters {

    @TypeConverter
    fun toBusRouteType(value: String): BusRouteType {
        return BusRouteType.valueOf(value)
    }

    @TypeConverter
    fun toString(value: BusRouteType): String {
        return value.name
    }
}
