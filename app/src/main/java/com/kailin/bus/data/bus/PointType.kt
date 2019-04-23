package com.kailin.bus.data.bus

import com.google.gson.annotations.SerializedName

/**
 * PointType {
 * PositionLat (number, optional): 位置緯度(WGS84) ,
 * PositionLon (number, optional): 位置經度(WGS84)
 * }
 */
class PointType {

    @SerializedName("PositionLon")
    var positionLon: String? = null

    @SerializedName("PositionLat")
    var positionLat: String? = null

    override fun toString(): String {
        return "PointType{" +
                "PositionLon='" + positionLon + '\''.toString() +
                ", PositionLat='" + positionLat + '\''.toString() +
                '}'.toString()
    }
}
