package com.kailin.bus.data.bus.version

import com.kailin.bus.data.bus.DateTypeConverters

import java.util.Date
import java.util.Objects
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName

/**
 * BusVersion {
 * VersionID (integer): 資料版本編號 ,
 * UpdateTime (DateTime): 資料更新日期時間(ISO8601格式:yyyy-MM-ddTHH:mm:sszzz) ,
 * UpdateCheckTime (DateTime): 資料最後檢核更新之日期時間(ISO8601格式:yyyy-MM-ddTHH:mm:sszzz)
 * }
 */
@Entity(tableName = BusVersion.ROOM_TABLE_NAME)
class BusVersion  : Comparable<BusVersion> {

    @PrimaryKey
    lateinit var busCity: String

    @SerializedName("VersionID")
    var versionID: Int = 0

    @SerializedName("UpdateTime")
    @TypeConverters(DateTypeConverters::class)
    var updateTime: Date? = null

    @SerializedName("UpdateCheckTime")
    @TypeConverters(DateTypeConverters::class)
    var updateCheckTime: Date? = null

    override fun toString(): String {
        return "BusVersion{" +
                "BusCity='" + busCity + '\''.toString() +
                ", VersionID=" + versionID +
                ", UpdateTime=" + updateTime +
                ", UpdateCheckTime=" + updateCheckTime +
                '}'.toString()
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as BusVersion?
        return versionID == that!!.versionID && busCity == that.busCity
    }

    override fun hashCode(): Int {
        return Objects.hash(busCity, versionID)
    }


    override fun compareTo(busVersion: BusVersion): Int {
        val result: Int
        if (versionID > busVersion.versionID) {
            result = 1
        } else if (versionID < busVersion.versionID) {
            result = -1
        } else {
            result = 0
        }
        return result
    }

    companion object {

        const val ROOM_TABLE_NAME = "BusVersion"
    }
}
