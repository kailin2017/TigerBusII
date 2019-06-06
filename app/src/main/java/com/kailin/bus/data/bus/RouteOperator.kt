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
data class RouteOperator(
        @SerializedName("operatorID") var operatorID: String? = "",
        @SerializedName("OperatorName") var operatorName: NameType? = NameType(),
        @SerializedName("OperatorCode") var operatorCode: String? = "",
        @SerializedName("OperatorNo") var operatorNo: String? = ""
) {

    override fun toString(): String {
        return "RouteOperator(operatorID=$operatorID, operatorName=$operatorName, operatorCode=$operatorCode, operatorNo=$operatorNo)"
    }
}
