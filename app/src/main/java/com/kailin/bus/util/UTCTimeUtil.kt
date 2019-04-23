package com.kailin.bus.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone
import java.util.concurrent.atomic.AtomicReference

class UTCTimeUtil private constructor() {

    val utcTime: String
        get() {
            val calendar = Calendar.getInstance()
            val dateFormat = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US)
            dateFormat.timeZone = TimeZone.getTimeZone("GMT")
            return dateFormat.format(calendar.time)
        }

    companion object {

        private val reference = AtomicReference<UTCTimeUtil>()

        val instance: UTCTimeUtil
            get() {
                while (true) {
                    var instance: UTCTimeUtil? = reference.get()
                    if (instance != null)
                        return instance

                    instance = UTCTimeUtil()
                    if (reference.compareAndSet(null, instance))
                        return instance
                }
            }
    }
}
