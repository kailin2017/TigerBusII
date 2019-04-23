package com.kailin.bus.data.bus

import androidx.room.TypeConverter
import java.util.*

class DirectionTypeConverters {

    @TypeConverter
    fun revertDirection(value: String): Direction {
        return Direction.valueOf(value)
    }

    @TypeConverter
    fun converterDirection(value: Direction): String {
        return value.name
    }
}
