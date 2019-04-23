package com.kailin.bus.data.bus.route

import com.google.gson.annotations.SerializedName

/**
 * 11: 市區公車', '12: 公路客運', '13: 國道客運', '14: 接駁車
 */
enum class BusRouteType {

    @SerializedName("11")
    URBAN_BUS,

    @SerializedName("12")
    ROAD_BUS,

    @SerializedName("13")
    HIGHWAY_BUS,

    @SerializedName("14")
    SHUTTLE_BUS
}
