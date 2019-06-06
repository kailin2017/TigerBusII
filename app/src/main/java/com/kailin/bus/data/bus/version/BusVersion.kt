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

    @TypeConverters(DateTypeConverters::class)
    @SerializedName("UpdateTime")
    var updateTime: Date? = Date()

    @TypeConverters(DateTypeConverters::class)
    @SerializedName("UpdateCheckTime")
    var updateCheckTime: Date? = Date()

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
        return when {
            versionID > busVersion.versionID -> 1
            versionID < busVersion.versionID -> -1
            else -> 0
        }
    }

    override fun toString(): String {
        return "BusVersion(busCity='$busCity', versionID=$versionID, updateTime=$updateTime, updateCheckTime=$updateCheckTime)"
    }

    companion object {

        const val ROOM_TABLE_NAME = "BusVersion"
    }
}
