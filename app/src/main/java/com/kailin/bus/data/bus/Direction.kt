package com.kailin.bus.data.bus

import androidx.annotation.StringRes
import com.google.gson.annotations.SerializedName
import com.kailin.bus.R

/**
 * Enum:	"0: 去程", "1: 返程", "2: 迴圈", "255: 未知"
 */
enum class Direction(@StringRes value: Int) {


    @SerializedName("0")
    GOING(R.string.direction_going),

    @SerializedName("1")
    RETURN(R.string.direction_return),

    @SerializedName("2")
    CYCLE(R.string.direction_cycle),

    @SerializedName("255")
    UNKNOWN(R.string.direction_unknown);
}
