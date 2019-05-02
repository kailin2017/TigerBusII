package com.kailin.architecture_model.util

import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

import java.util.concurrent.atomic.AtomicReference

class LoggerUtil private constructor() {

    init {
        Logger.addLogAdapter(AndroidLogAdapter())
    }

    fun log(priority: Int, tag: String?, message: String?, throwable: Throwable?) {
        Logger.log(priority, tag, message, throwable)
    }

    fun d(message: String, vararg args: Any) {
        Logger.d(message, *args)
    }

    fun d(`object`: Any?) {
        Logger.d(`object`)
    }

    fun e(message: String, vararg args: Any) {
        Logger.e(null, message, *args)
    }

    fun e(throwable: Throwable?, message: String, vararg args: Any) {
        Logger.e(throwable, message, *args)
    }

    fun i(message: String, vararg args: Any) {
        Logger.i(message, *args)
    }

    fun v(message: String, vararg args: Any) {
        Logger.v(message, *args)
    }

    fun w(message: String, vararg args: Any) {
        Logger.w(message, *args)
    }

    fun wtf(message: String, vararg args: Any) {
        Logger.wtf(message, *args)
    }

    fun json(json: String?) {
        Logger.json(json)
    }

    fun xml(xml: String?) {
        Logger.xml(xml)
    }

    companion object {

        private val reference = AtomicReference<LoggerUtil>()

        val instance: LoggerUtil
            get() {
                while (true) {
                    var instance: LoggerUtil? = reference.get()
                    if (instance != null)
                        return instance

                    instance = LoggerUtil()
                    if (reference.compareAndSet(null, instance))
                        return instance
                }
            }
    }
}
