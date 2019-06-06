package com.kailin.bus.data.bus.display

import androidx.annotation.StringRes
import com.google.gson.annotations.SerializedName
import com.kailin.bus.R

/**
 * ['0: 可上下車', '1: 可上車', '-1: 可下車']
 */
enum class StopBoarding(@StringRes value: Int) {

    @SerializedName("0")
    GET_ON_AND_OFF(R.string.boarding_get_on_and_off),

    @SerializedName("1")
    GET_ON(R.string.boarding_get_on),

    @SerializedName("-1")
    GET_OFF(R.string.boarding_get_off)
}