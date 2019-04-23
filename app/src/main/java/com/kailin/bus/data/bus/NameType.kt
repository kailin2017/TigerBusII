package com.kailin.bus.data.bus

import com.google.gson.annotations.SerializedName

/**
 * NameType {
 * Zh_tw (string, optional): 中文繁體名稱 ,
 * En (string, optional): 英文名稱
 * }
 */

class NameType {

    @SerializedName("Zh_tw")
    var zh_tw: String? = null

    @SerializedName("En")
    var en: String? = null

    override fun toString(): String {
        return "NameType{" +
                "Zh_tw='" + zh_tw + '\''.toString() +
                ", En='" + en + '\''.toString() +
                '}'.toString()
    }
}
