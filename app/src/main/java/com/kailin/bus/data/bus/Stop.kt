package com.kailin.bus.data.bus

import com.google.gson.annotations.SerializedName

/**
 * Stop {
 * StopUID (string): 站牌唯一識別代碼，規則為 {業管機關簡碼} + {StopID}，其中 {業管機關簡碼} 可於Authority API中的AuthorityCode欄位查詢 ,
 * StopID (string): 地區既用中之站牌代碼(為原資料內碼) ,
 * StopName (NameType): 站牌名稱 ,
 * StationNameID (string): 站名碼 ,
 * StopBoarding (string, optional): 上下車站別 = ['0: 可上下車', '1: 可上車', '-1: 可下車'],
 * StopSequence (integer): 路線經過站牌之順序 ,
 * StopPosition (PointType): 站牌位置 ,
 * StationID (string, optional): 站牌所屬的站位ID ,
 * LocationCityCode (string, optional): 站牌位置縣市之代碼(國際ISO 3166-2 三碼城市代碼)[若為公路/國道客運路線則為空值]
 * }
 */
class Stop {

    @SerializedName("StopName")
    var stopName: NameType? = null

    @SerializedName("StopBoarding")
    var stopBoarding: String? = null

    @SerializedName("StopID")
    var stopID: String? = null

    @SerializedName("StopPosition")
    var stopPosition: PointType? = null

    @SerializedName("StopUID")
    var stopUID: String? = null

    @SerializedName("StationID")
    var stationID: String? = null

    @SerializedName("StopSequence")
    var stopSequence: String? = null

    override fun toString(): String {
        return "Stop{" +
                "StopName=" + stopName +
                ", StopBoarding='" + stopBoarding + '\''.toString() +
                ", StopID='" + stopID + '\''.toString() +
                ", StopPosition=" + stopPosition +
                ", StopUID='" + stopUID + '\''.toString() +
                ", StationID='" + stationID + '\''.toString() +
                ", StopSequence='" + stopSequence + '\''.toString() +
                '}'.toString()
    }
}
