package com.kailin.bus.data.bus.route

import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.kailin.bus.data.bus.DateTypeConverters
import com.kailin.bus.data.bus.Direction
import com.kailin.bus.data.bus.DirectionTypeConverters
import com.kailin.bus.data.bus.NameType
import java.util.*
import kotlin.collections.ArrayList

/**
 * BusSubRoute {
 * SubRouteUID (string): 附屬路線唯一識別代碼，規則為 {業管機關簡碼} + {SubRouteID}，其中 {業管機關簡碼} 可於Authority API中的AuthorityCode欄位查詢 ,
 * SubRouteID (string): 地區既用中之附屬路線代碼(為原資料內碼) ,
 * OperatorIDs (Array[string]): 營運業者代碼 ,
 * SubRouteName (NameType): 附屬路線名稱 ,
 * Headsign (string, optional): 車頭描述 ,
 * Direction (string): 去返程 = ['0: 去程', '1: 返程', '2: 迴圈', '255: 未知']
 * ,
 * FirstBusTime (string, optional): 平日第一班發車時間 ,
 * LastBusTime (string, optional): 平日返程第一班發車時間 ,
 * HolidayFirstBusTime (string, optional): 假日去程第一班發車時間 ,
 * HolidayLastBusTime (string, optional): 假日返程第一班發車時間
 * }
 */

data class BusSubRoute(
        @SerializedName("SubRouteUID") var subRouteUID: String? = "",
        @SerializedName("SubRouteID") var subRouteID: String? = "",
        @SerializedName("OperatorIDs") var operatorIDs: Array<String>? = arrayOf(""),
        @SerializedName("SubRouteName") var subRouteName: NameType? = NameType(),
        @SerializedName("Direction") var direction: Direction? = Direction.UNKNOWN,
        @SerializedName("FirstBusTime") var firstBusTime: String? = "",
        @SerializedName("LastBusTime") var lastBusTime: String? = "",
        @SerializedName("HolidayFirstBusTime") var holidayFirstBusTime: String? = "",
        @SerializedName("HolidayLastBusTime") var holidayLastBusTime: String? = ""
) {

    override fun toString(): String {
        return "BusSubRoute(subRouteUID=$subRouteUID, subRouteID=$subRouteID, operatorIDs=${Arrays.toString(operatorIDs)}, subRouteName=$subRouteName, direction=$direction, firstBusTime=$firstBusTime, lastBusTime=$lastBusTime, holidayFirstBusTime=$holidayFirstBusTime, holidayLastBusTime=$holidayLastBusTime)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BusSubRoute

        if (subRouteUID != other.subRouteUID) return false

        return true
    }

    override fun hashCode(): Int {
        return subRouteUID?.hashCode() ?: 0
    }
}
