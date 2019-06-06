package com.kailin.bus.data.bus

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.kailin.bus.util.GsonUtil

class NameTypeConverters {

    @TypeConverter
    fun revertNameType(value: String): NameType {
        val type = object : TypeToken<NameType>() {

        }.type
        val gson = GsonUtil.instance.gson
        return gson.fromJson<NameType>(value, type)
    }

    @TypeConverter
    fun converterString(value: NameType): String {
        return GsonUtil.instance.gson.toJson(value)
    }
}
