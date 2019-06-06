package com.kailin.bus.data.bus

import com.google.gson.annotations.SerializedName

/**
 * PointType {
 * PositionLat (number, optional): 位置緯度(WGS84) ,
 * PositionLon (number, optional): 位置經度(WGS84)
 * }
 */
data class PointType(
        @SerializedName("PositionLon") val positionLon: String? = "",
        @SerializedName("PositionLat") val positionLat: String? = ""
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PointType

        if (positionLon != other.positionLon) return false
        if (positionLat != other.positionLat) return false

        return true
    }

    override fun hashCode(): Int {
        var result = positionLon?.hashCode() ?: 0
        result = 31 * result + (positionLat?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "PointType(positionLon=$positionLon, positionLat=$positionLat)"
    }

}
