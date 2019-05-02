package com.kailin.bus.connect

import com.kailin.architecture_model.util.security.HMacUtil
import com.kailin.bus.util.UTCTimeUtil
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicReference

class MyInterceptor private constructor(private val appId: String, private val appKey: String) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val dateString = UTCTimeUtil.instance.utcTime

        val oldRequest = chain.request()
        val builder = oldRequest.newBuilder()

        val signature = HMacUtil.instance.encrypt(String.format("x-date: %s", dateString), appKey, HMacUtil.hmac_sha1)
        val authorization = String.format("hmac username=\"%s\", algorithm=\"hmac-sha1\", headers=\"x-date\", signature=\"%s\"", appId, signature)

        builder.addHeader("Authorization", authorization)
        builder.addHeader("x-date", dateString)

        val request = builder.build()

        return chain.proceed(request)
    }

    companion object {

        private val hashMap = ConcurrentHashMap<String, AtomicReference<MyInterceptor>>()

        fun getInstance(appId: String, appKey: String): MyInterceptor {
            var reference = hashMap[appId]
            if (reference == null) {
                reference = AtomicReference()
                hashMap[appId] = reference
            }
            while (true) {
                var interceptor: MyInterceptor? = reference.get()
                if (interceptor != null) {
                    return interceptor
                }

                interceptor = MyInterceptor(appId, appKey)
                if (reference.compareAndSet(null, interceptor)) {
                    return interceptor
                }
            }
        }
    }
}
