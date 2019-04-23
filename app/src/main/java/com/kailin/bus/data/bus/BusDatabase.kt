package com.kailin.bus.data.bus

import android.content.Context

import com.kailin.bus.data.bus.route.BusRoute
import com.kailin.bus.data.bus.route.BusRouteDao
import com.kailin.bus.data.bus.version.BusVersion
import com.kailin.bus.data.bus.version.BusVersionDao

import java.util.concurrent.atomic.AtomicReference

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BusVersion::class, BusRoute::class], version = 1, exportSchema = false)
abstract class BusDatabase : RoomDatabase() {

    abstract fun busRouteDao(): BusRouteDao

    abstract fun busVersionDao(): BusVersionDao

    // close後已無法使用,必須將reference同步清除
    override fun close() {
        super.close()
        reference.compareAndSet(this, null)
    }

    companion object {

        private val reference = AtomicReference<BusDatabase>()

        fun createInstance(context: Context): BusDatabase {
            return Room.databaseBuilder(context, BusDatabase::class.java, "BusDatabase.db").build()
        }

        fun getInstance(context: Context): BusDatabase {
            while (true) {
                var instance: BusDatabase? = reference.get()
                if (instance != null)
                    return instance

                instance = createInstance(context)
                if (reference.compareAndSet(null, instance))
                    return instance
            }
        }
    }
}
