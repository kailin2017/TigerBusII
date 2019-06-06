package com.kailin.bus.data.bus.estimate

import androidx.annotation.StringRes
import com.google.gson.annotations.SerializedName
import com.kailin.bus.R

/**
 *  StopStatus (string, optional): 車輛狀態備註 = ['0: 正常', '1: 尚未發車', '2: 交管不停靠', '3: 末班車已過', '4: 今日未營運'],
 */
enum class BusStopStatus(@StringRes val stringRes: Int) {

    @SerializedName("0")
    OK(R.string.bottomNavigationView),

    @SerializedName("1")
    NO(R.string.bottomNavigationView),

    @SerializedName("2")
    NO_STOP(R.string.bottomNavigationView),

    @SerializedName("3")
    OFF(R.string.bottomNavigationView),

    @SerializedName("4")
    TODAY_OFF(R.string.bottomNavigationView)
}