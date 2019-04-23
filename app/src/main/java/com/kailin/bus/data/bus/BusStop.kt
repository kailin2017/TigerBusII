package com.kailin.bus.data.bus

import com.google.gson.annotations.SerializedName
/**
 * BusStop {
 * StopUID (string): 站牌唯一識別代碼，規則為 {業管機關簡碼} + {StopID}，其中 {業管機關簡碼} 可於Authority API中的AuthorityCode欄位查詢 ,
 * StopID (string): 地區既用中之站牌代碼(為原資料內碼) ,
 * AuthorityID (string): 業管機關代碼 ,
 * StopName (NameType): 站牌名稱 ,
 * StopPosition (PointType): 站牌位置 ,
 * StopAddress (string, optional): 站牌地址 ,
 * Bearing (string, optional): 方位角，E:東行;W:西行;S:南行;N:北行;SE:東南行;NE:東北行;SW:西南行;NW:西北行 ,
 * StationID (string, optional): 站牌所屬的站位ID ,
 * StopDescription (string, optional): 站牌詳細說明描述 ,
 * BusCity (string, optional): 站牌權管所屬縣市(相當於市區公車API的City參數)[若為公路/國道客運路線則為空值] ,
 * CityCode (string, optional): 站牌權管所屬縣市之代碼(國際ISO 3166-2 三碼城市代碼)[若為公路/國道客運路線則為空值] ,
 * LocationCityCode (string, optional): 站牌位置縣市之代碼(國際ISO 3166-2 三碼城市代碼)[若為公路/國道客運路線則為空值] ,
 * UpdateTime (DateTime): 資料更新日期時間(ISO8601格式:yyyy-MM-ddTHH:mm:sszzz) ,
 * VersionID (integer): 資料版本編號
 * }
 * NameType {
 * Zh_tw (string, optional): 中文繁體名稱 ,
 * En (string, optional): 英文名稱
 * }
 * PointType {
 * PositionLat (number, optional): 位置緯度(WGS84) ,
 * PositionLon (number, optional): 位置經度(WGS84)
 * }
 */
class BusStop {

    @SerializedName("StopName")
    var stopName: NameType? = null

    @SerializedName("CityCode")
    var cityCode: String? = null

    @SerializedName("VersionID")
    var versionID: String? = null

    @SerializedName("StopID")
    var stopID: String? = null

    @SerializedName("StopPosition")
    var stopPosition: PointType? = null

    @SerializedName("AuthorityID")
    var authorityID: String? = null

    @SerializedName("StopUID")
    var stopUID: String? = null

    @SerializedName("City")
    var city: String? = null

    @SerializedName("StationID")
    var stationID: String? = null

    @SerializedName("Bearing")
    var bearing: String? = null

    @SerializedName("LocationCityCode")
    var locationCityCode: String? = null

    @SerializedName("UpdateTime")
    var updateTime: String? = null

    @SerializedName("StopAddress")
    var stopAddress: String? = null

    override fun toString(): String {
        return "BusStop(stopName=$stopName, cityCode=$cityCode, versionID=$versionID, stopID=$stopID, stopPosition=$stopPosition, authorityID=$authorityID, stopUID=$stopUID, city=$city, stationID=$stationID, bearing=$bearing, locationCityCode=$locationCityCode, updateTime=$updateTime, stopAddress=$stopAddress)"
    }
}
