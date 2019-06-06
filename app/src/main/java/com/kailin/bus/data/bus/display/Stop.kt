package com.kailin.bus.data.bus.display

import com.google.gson.annotations.SerializedName
import com.kailin.bus.data.bus.NameType
import com.kailin.bus.data.bus.PointType

/**
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
 */
data class Stop(
        @SerializedName("StopUID") val stopUID: String? = "",
        @SerializedName("StopID") val stopID: String? = "",
        @SerializedName("StopName") val stopName: NameType? = NameType(),
        @SerializedName("StopBoarding") val stopBoarding: StopBoarding? = StopBoarding.GET_ON_AND_OFF,
        @SerializedName("StopSequence") val stopSequence: Int = 0,
        @SerializedName("StopPosition") val stopPosition: PointType? = PointType(),
        @SerializedName("StationID") val stationID: String? = "",
        @SerializedName("StationNameID") val stationNameID: String? = ""
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Stop

        if (stopUID != other.stopUID) return false
        if (stopID != other.stopID) return false
        if (stopSequence != other.stopSequence) return false

        return true
    }

    override fun hashCode(): Int {
        var result = stopUID?.hashCode() ?: 0
        result = 31 * result + (stopID?.hashCode() ?: 0)
        result = 31 * result + stopSequence
        return result
    }

    override fun toString(): String {
        return "Stop(stopUID=$stopUID, stopID=$stopID, stopName=$stopName, stopBoarding=$stopBoarding, stopSequence=$stopSequence, stopPosition=$stopPosition, stationID=$stationID, stationNameID=$stationNameID)"
    }
}
