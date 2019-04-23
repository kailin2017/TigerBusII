package com.kailin.bus.data.bus.route

import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.kailin.bus.data.bus.DateTypeConverters
import com.kailin.bus.data.bus.Direction
import com.kailin.bus.data.bus.DirectionTypeConverters
import com.kailin.bus.data.bus.NameType
import java.util.*

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

class BusSubRoute {

    @SerializedName("SubRouteUID")
    var subRouteUID: String? = null

    @SerializedName("SubRouteID")
    var subRouteID: String? = null

    @SerializedName("OperatorIDs")
    var operatorIDs: Array<String>? = null

    @SerializedName("SubRouteName")
    var subRouteName: NameType? = null

    @SerializedName("Direction")
    @TypeConverters(DirectionTypeConverters::class)
    var direction: Direction? = null

    @SerializedName("FirstBusTime")
    var firstBusTime: String? = null

    @SerializedName("LastBusTime")
    var lastBusTime: String? = null

    @SerializedName("HolidayFirstBusTime")
    var holidayFirstBusTime: String? = null

    @SerializedName("HolidayLastBusTime")
    var holidayLastBusTime: String? = null

    override fun toString(): String {
        return "BusSubRoute(subRouteUID=$subRouteUID, subRouteID=$subRouteID, operatorIDs=${Arrays.toString(operatorIDs)}, subRouteName=$subRouteName, direction=$direction, firstBusTime=$firstBusTime, lastBusTime=$lastBusTime, holidayFirstBusTime=$holidayFirstBusTime, holidayLastBusTime=$holidayLastBusTime)"
    }
}
