package com.kailin.bus.data.bus.route

import com.kailin.bus.data.bus.BusCity
import com.kailin.bus.data.bus.BusCityConverters

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.TypeConverters
import androidx.room.Update
import io.reactivex.Observable

@Dao
interface BusRouteDao {

    @get:Query("SELECT count(*) FROM BusRoute")
    val busRouteCount: Int

    @get:Query("SELECT * FROM BusRoute")
    val busRoutes: Observable<List<BusRoute>>

    @Query("SELECT count(*) FROM BusRoute WHERE VersionID < :versionId")
    fun getOldBusRoute(versionId: Int): Int

    @Query("SELECT * FROM BusRoute WHERE RouteName LIKE '%' || :routeName || '%' LIMIT 50")
    fun getBusRoutes(routeName: String): Observable<List<BusRoute>>

    @Query("SELECT * FROM BusRoute WHERE City IN (:busCities) AND RouteName LIKE '%' || :routeName || '%' LIMIT 50")
    fun getBusRoutes(routeName: String, @TypeConverters(BusCityConverters::class) vararg busCities: BusCity): Observable<List<BusRoute>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(busRoutes: List<BusRoute>): List<Long>

    @Update
    fun update(vararg busRoutes: BusRoute): Int

    @Delete
    fun delete(vararg busRoutes: BusRoute): Int
}
