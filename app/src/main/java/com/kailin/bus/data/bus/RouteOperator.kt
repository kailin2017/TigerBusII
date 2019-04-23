package com.kailin.bus.data.bus

import com.google.gson.annotations.SerializedName

/**
 * RouteOperator {
 * OperatorID (string): 營運業者代碼 ,
 * OperatorName (NameType): 營運業者名稱 ,
 * OperatorCode (string): 營運業者簡碼 ,
 * OperatorNo (string): 營運業者編號[交通部票證資料系統定義]
 * }
 */
class RouteOperator {

    @SerializedName("operatorID")
    var operatorID: String? = null

    @SerializedName("OperatorName")
    var operatorName: NameType? = null

    @SerializedName("OperatorCode")
    var operatorCode: String? = null

    @SerializedName("OperatorNo")
    var operatorNo: String? = null

    override fun toString(): String {
        return "RouteOperator{" +
                "OperatorID='" + operatorID + '\''.toString() +
                ", OperatorName=" + operatorName +
                ", OperatorCode='" + operatorCode + '\''.toString() +
                ", OperatorNo='" + operatorNo + '\''.toString() +
                '}'.toString()
    }
}
