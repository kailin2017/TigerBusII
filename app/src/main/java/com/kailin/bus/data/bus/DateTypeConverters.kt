package com.kailin.bus.data.bus

import androidx.room.TypeConverter
import java.util.*

class DateTypeConverters {

    @TypeConverter
    fun revertDate(value: Long): Date {
        return Date(value)
    }

    @TypeConverter
    fun converterDate(value: Date): Long {
        return value.time
    }
}
