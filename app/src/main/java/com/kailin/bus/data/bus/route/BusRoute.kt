package com.kailin.bus.data.bus.route


import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.NonNull
import com.google.gson.annotations.SerializedName
import com.kailin.bus.data.bus.BusCity
import com.kailin.bus.data.bus.BusCityConverters
import com.kailin.bus.data.bus.DateTypeConverters
import com.kailin.bus.data.bus.NameType
import com.kailin.bus.data.bus.NameTypeConverters
import com.kailin.bus.data.bus.RouteOperator
import com.kailin.bus.data.bus.RouteOperatorConverters

import java.util.Date
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import kotlin.collections.ArrayList

/**
 * BusRoute {
 * RouteUID (string): 路線唯一識別代碼，規則為 {業管機關簡碼} + {RouteID}，其中 {業管機關簡碼} 可於Authority API中的AuthorityCode欄位查詢 ,
 * RouteID (string): 地區既用中之路線代碼(為原資料內碼) ,
 * HasSubRoutes (boolean): 實際上是否有多條附屬路線。(此欄位值與SubRoutes結構並無強烈的絕對關聯。詳細說明請參閱swagger上方的【資料服務使用注意事項】) ,
 * RouteOperator (Array[RouteOperator]): 營運業者 ,
 * AuthorityID (string): 業管機關代碼 ,
 * ProviderID (string): 資料提供平台代碼 ,
 * BusSubRoute (Array[BusSubRoute], optional): 附屬路線資料(如果原始資料並無提供附屬路線ID，而本平台基於跨來源資料之一致性，會以SubRouteID=RouteID產製一份相對應的附屬路線資料(若有去返程，則會有兩筆)) ,
 * BusRouteType (string): 公車路線類別 = ['11: 市區公車', '12: 公路客運', '13: 國道客運', '14: 接駁車'],
 * RouteName (NameType): 路線名稱 ,
 * DepartureStopNameZh (string, optional): 起站中文名稱 ,
 * DepartureStopNameEn (string, optional): 起站英文名稱 ,
 * DestinationStopNameZh (string, optional): 終點站中文名稱 ,
 * DestinationStopNameEn (string, optional): 終點站英文名稱 ,
 * TicketPriceDescriptionZh (string, optional): 票價中文敘述 ,
 * TicketPriceDescriptionEn (string, optional): 票價英文敘述 ,
 * FareBufferZoneDescriptionZh (string, optional): 收費緩衝區中文敘述 ,
 * FareBufferZoneDescriptionEn (string, optional): 收費緩衝區英文敘述 ,
 * RouteMapImageUrl (string, optional): 路線簡圖網址 ,
 * City (string, optional): 路線權管所屬縣市(相當於市區公車API的City參數)[若為公路/國道客運路線則為空值] ,
 * CityCode (string, optional): 路線權管所屬縣市之代碼(國際ISO 3166-2 三碼城市代碼)[若為公路/國道客運路線則為空值] ,
 * UpdateTime (DateTime): 資料更新日期時間(ISO8601格式:yyyy-MM-ddTHH:mm:sszzz) ,
 * VersionID (integer): 資料版本編號
 * }
 * RouteOperator {
 * OperatorID (string): 營運業者代碼 ,
 * NameType (NameType): 營運業者名稱 ,
 * OperatorCode (string): 營運業者簡碼 ,
 * OperatorNo (string): 營運業者編號[交通部票證資料系統定義]
 * }
 * BusSubRoute {
 * SubRouteUID (string): 附屬路線唯一識別代碼，規則為 {業管機關簡碼} + {SubRouteID}，其中 {業管機關簡碼} 可於Authority API中的AuthorityCode欄位查詢 ,
 * SubRouteID (string): 地區既用中之附屬路線代碼(為原資料內碼) ,
 * OperatorIDs (Array[string]): 營運業者代碼 ,
 * SubRouteName (NameType): 附屬路線名稱 ,
 * Headsign (string, optional): 車頭描述 ,
 * Direction (string): 去返程 = ['0: 去程', '1: 返程', '2: 迴圈', '255: 未知'],
 * FirstBusTime (string, optional): 平日第一班發車時間 ,
 * LastBusTime (string, optional): 平日返程第一班發車時間 ,
 * HolidayFirstBusTime (string, optional): 假日去程第一班發車時間 ,
 * HolidayLastBusTime (string, optional): 假日返程第一班發車時間
 * }
 * NameType {
 * Zh_tw (string, optional): 中文繁體名稱 ,
 * En (string, optional): 英文名稱
 * }
 */
@Entity(tableName = BusRoute.ROOM_TABLE_NAME)
class BusRoute() : Parcelable {

    @PrimaryKey
    @NonNull
    @SerializedName("RouteUID")
    var routeUID: String? = ""

    @SerializedName("RouteID")
    var routeID: String? = ""

    @SerializedName("HasSubRoutes")
    var hasSubRoutes: Boolean = false

    @TypeConverters(RouteOperatorConverters::class)
    @SerializedName("Operators")
    var operators: ArrayList<RouteOperator>? = ArrayList()

    @SerializedName("AuthorityID")
    var authorityID: String? = ""

    @SerializedName("ProviderID")
    var providerID: String? = ""

    @TypeConverters(BusSubRouteConverters::class)
    @SerializedName("SubRoutes")
    var subRoutes: ArrayList<BusSubRoute>? = ArrayList()

    @TypeConverters(BusRouteTypeConverters::class)
    @SerializedName("BusRouteType")
    var busRouteType: BusRouteType? = BusRouteType.URBAN_BUS

    @TypeConverters(NameTypeConverters::class)
    @SerializedName("RouteName")
    var routeName: NameType? = NameType()

    @SerializedName("DepartureStopNameZh")
    var departureStopNameZh: String? = ""

    @SerializedName("DepartureStopNameEn")
    var departureStopNameEn: String? = ""

    @SerializedName("DestinationStopNameZh")
    var destinationStopNameZh: String? = ""

    @SerializedName("DestinationStopNameEn")
    var destinationStopNameEn: String? = ""

    @SerializedName("TicketPriceDescriptionZh")
    var ticketPriceDescriptionZh: String? = ""

    @SerializedName("TicketPriceDescriptionEn")
    var ticketPriceDescriptionEn: String? = ""

    @SerializedName("FareBufferZoneDescriptionZh")
    var fareBufferZoneDescriptionZh: String? = ""

    @SerializedName("FareBufferZoneDescriptionEn")
    var fareBufferZoneDescriptionEn: String? = ""

    @SerializedName("RouteMapImageUrl")
    var routeMapImageUrl: String? = ""

    @TypeConverters(BusCityConverters::class)
    @SerializedName("City")
    var city: BusCity? = BusCity.Taipei

    @SerializedName("CityCode")
    var cityCode: String? = ""

    @TypeConverters(DateTypeConverters::class)
    @SerializedName("UpdateTime")
    var updateTime: Date? = Date()

    @SerializedName("VersionID")
    var versionID: Int = 0

    constructor(parcel: Parcel) : this() {
        routeUID = parcel.readString()
        routeID = parcel.readString()
        hasSubRoutes = parcel.readByte() != 0.toByte()
        authorityID = parcel.readString()
        providerID = parcel.readString()
        departureStopNameZh = parcel.readString()
        departureStopNameEn = parcel.readString()
        destinationStopNameZh = parcel.readString()
        destinationStopNameEn = parcel.readString()
        ticketPriceDescriptionZh = parcel.readString()
        ticketPriceDescriptionEn = parcel.readString()
        fareBufferZoneDescriptionZh = parcel.readString()
        fareBufferZoneDescriptionEn = parcel.readString()
        routeMapImageUrl = parcel.readString()
        cityCode = parcel.readString()
        versionID = parcel.readInt()
    }

    override fun toString(): String {
        return "BusRoute(routeUID='$routeUID', routeID=$routeID, hasSubRoutes=$hasSubRoutes, operators=$operators, authorityID=$authorityID, providerID=$providerID, subRoutes=$subRoutes, busRouteType=$busRouteType, routeName=$routeName, departureStopNameZh=$departureStopNameZh, departureStopNameEn=$departureStopNameEn, destinationStopNameZh=$destinationStopNameZh, destinationStopNameEn=$destinationStopNameEn, ticketPriceDescriptionZh=$ticketPriceDescriptionZh, ticketPriceDescriptionEn=$ticketPriceDescriptionEn, fareBufferZoneDescriptionZh=$fareBufferZoneDescriptionZh, fareBufferZoneDescriptionEn=$fareBufferZoneDescriptionEn, routeMapImageUrl=$routeMapImageUrl, city=$city, cityCode=$cityCode, updateTime=$updateTime, versionID=$versionID)"
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(routeUID)
        parcel.writeString(routeID)
        parcel.writeByte(if (hasSubRoutes) 1 else 0)
        parcel.writeString(authorityID)
        parcel.writeString(providerID)
        parcel.writeString(departureStopNameZh)
        parcel.writeString(departureStopNameEn)
        parcel.writeString(destinationStopNameZh)
        parcel.writeString(destinationStopNameEn)
        parcel.writeString(ticketPriceDescriptionZh)
        parcel.writeString(ticketPriceDescriptionEn)
        parcel.writeString(fareBufferZoneDescriptionZh)
        parcel.writeString(fareBufferZoneDescriptionEn)
        parcel.writeString(routeMapImageUrl)
        parcel.writeString(cityCode)
        parcel.writeInt(versionID)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BusRoute> {

        const val ROOM_TABLE_NAME = "BusRoute"

        const val BUS_ROUTE = "BusRoute"

        override fun createFromParcel(parcel: Parcel): BusRoute {
            return BusRoute(parcel)
        }

        override fun newArray(size: Int): Array<BusRoute?> {
            return arrayOfNulls(size)
        }
    }
}
