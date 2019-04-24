package com.kailin.utillibrary

import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.util.concurrent.atomic.AtomicReference

class CheckRootUtil private constructor(){

    private val rootPaths = arrayOf(
        "/sbin/",
        "/system/bin/",
        "/system/xbin/",
        "/data/local/xbin/",
        "/data/local/bin/",
        "/system/sd/xbin/",
        "/system/bin/failsafe/",
        "/data/local/"
    )

    val isRooted: Boolean
        get() = checkRootMethod1() || checkRootMethod2() || checkRootMethod3()

    private fun checkRootMethod1(): Boolean {
        val buildTags = android.os.Build.TAGS
        return buildTags != null && buildTags.contains("test-keys")
    }

    private fun checkRootMethod2(): Boolean {
        for (path in rootPaths) {
            val file = File("$path/su")
            if (file.exists())
                return true
        }
        return false
    }

    private fun checkRootMethod3(): Boolean {
        var process: Process? = null
        return try {
            process = Runtime.getRuntime().exec(arrayOf("/system/xbin/which", "su"))
            BufferedReader(InputStreamReader(process!!.inputStream)).readLine() != null
        } catch (t: Throwable) {
            false
        } finally {
            process?.destroy()
        }
    }

    companion object {

        private val reference = AtomicReference<CheckRootUtil>()

        fun instance(): CheckRootUtil {
            while (true) {
                var checkRootUtil: CheckRootUtil? = reference.get()
                if (checkRootUtil != null)
                    return checkRootUtil

                checkRootUtil = CheckRootUtil()
                if (reference.compareAndSet(null, checkRootUtil))
                    return checkRootUtil
            }
        }
    }
}
