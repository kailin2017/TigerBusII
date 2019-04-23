package com.kailin.bus.data.bus

import androidx.room.TypeConverter

class BusStopStatusTypeConverters {

    @TypeConverter
    fun revertBusStopStatus(value: String): BusStopStatus {
        return BusStopStatus.valueOf(value)
    }

    @TypeConverter
    fun converterString(value: BusStopStatus): String {
        return value.name
    }
}
