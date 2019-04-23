package com.kailin.bus.data.bus

import com.google.gson.annotations.SerializedName

/**
 * Enum:	"0: 去程", "1: 返程", "2: 迴圈", "255: 未知"
 */
enum class Direction(value: Int) {


    @SerializedName("0")
    GOING(0),

    @SerializedName("1")
    RETURN(1),

    @SerializedName("2")
    CYCLE(2),

    @SerializedName("255")
    UNKNOWN(255);
}
