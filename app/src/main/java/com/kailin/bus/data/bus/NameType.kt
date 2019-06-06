package com.kailin.bus.data.bus

import com.google.gson.annotations.SerializedName

/**
 * NameType {
 * Zh_tw (string, optional): 中文繁體名稱 ,
 * En (string, optional): 英文名稱
 * }
 */

data class NameType(
        @SerializedName("Zh_tw") val zh_tw: String? = "高雄宇宙港",
        @SerializedName("En") val en: String? = ""
) {
    override fun toString(): String {
        return "NameType(zh_tw=$zh_tw, en=$en)"
    }
}
