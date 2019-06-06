package com.kailin.bus.data.bus.display

import com.google.gson.annotations.SerializedName
import com.kailin.bus.data.bus.Direction
import com.kailin.bus.data.bus.NameType
import java.util.*
import kotlin.collections.ArrayList


/**
Inline Model [
BusDisplayStopOfRoute
]
BusDisplayStopOfRoute {
RouteUID (string): 路線唯一識別代碼，規則為 {業管機關代碼} + {RouteID}，其中 {業管機關代碼} 可於Authority API中的AuthorityCode欄位查詢 ,
RouteID (string): 地區既用中之路線代碼(為原資料內碼) ,
RouteName (NameType): 路線名稱 ,
Direction (string, optional): 去返程 = ['0: 去程', '1: 返程', '2: 迴圈', '255: 未知'],
Stops (Array[Stop]): 所有經過站牌 ,
UpdateTime (DateTime): 資料更新日期時間(ISO8601格式:yyyy-MM-ddTHH:mm:sszzz) ,
VersionID (integer): 資料版本編號
}
NameType {
Zh_tw (string, optional): 中文繁體名稱 ,
En (string, optional): 英文名稱
}
Stop {
StopUID (string): 站牌唯一識別代碼，規則為 {業管機關簡碼} + {StopID}，其中 {業管機關簡碼} 可於Authority API中的AuthorityCode欄位查詢 ,
StopID (string): 地區既用中之站牌代碼(為原資料內碼) ,
StopName (NameType): 站牌名稱 ,
StopBoarding (string, optional): 上下車站別 = ['0: 可上下車', '1: 可上車', '-1: 可下車'],
StopSequence (integer): 路線經過站牌之順序 ,
StopPosition (PointType): 站牌位置 ,
StationID (string, optional): 站牌所屬的站位ID ,
StationNameID (string): 站牌所屬的站名碼ID ,
LocationCityCode (string, optional): 站牌位置縣市之代碼(國際ISO 3166-2 三碼城市代碼)[若為公路/國道客運路線則為空值]
}
PointType {
PositionLat (number, optional): 位置緯度(WGS84) ,
PositionLon (number, optional): 位置經度(WGS84)
} */
data class BusDisplayStopOfRoute(
        @SerializedName("RouteUID") val routeUID: String? = "",
        @SerializedName("RouteID") val routeID: String? = "",
        @SerializedName("RouteName") val routeName: NameType? = NameType(),
        @SerializedName("Direction") val direction: Direction? = Direction.UNKNOWN,
        @SerializedName("Stops") val stops: List<Stop>? = ArrayList(),
        @SerializedName("UpdateTime") val updateTime: Date? = Date(),
        @SerializedName("VersionID") val versionID: Int? = 0
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BusDisplayStopOfRoute

        if (routeUID != other.routeUID) return false
        if (direction != other.direction) return false

        return true
    }

    override fun hashCode(): Int {
        var result = routeUID?.hashCode() ?: 0
        result = 31 * result + (direction?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "BusDisplayStopOfRoute(routeUID=$routeUID, routeID=$routeID, routeName=$routeName, direction=$direction, stops=$stops, updateTime=$updateTime, versionID=$versionID)"
    }
}