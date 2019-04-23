package com.kailin.bus.data.bus.version

import com.kailin.bus.data.bus.DateTypeConverters

import java.util.Date

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.TypeConverters
import androidx.room.Update

@Dao
interface BusVersionDao {

    @Query("SELECT * FROM BUSVERSION WHERE BusCity = :BusCity AND UpdateTime < :UpdateTime")
    fun getBusVersion(BusCity: String, @TypeConverters(DateTypeConverters::class) UpdateTime: Date): BusVersion

    @Query("SELECT * FROM BUSVERSION WHERE BusCity = :BusCity")
    fun getBusVersion(BusCity: String): BusVersion

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg busVersions: BusVersion): List<Long>

    @Update
    fun update(vararg busVersions: BusVersion): Int

    @Delete
    fun delete(vararg busVersions: BusVersion): Int
}
