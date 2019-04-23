package com.kailin.bus.connect

import com.kailin.bus.MyApplication
import com.kailin.bus.R
import com.kailin.bus.util.GsonUtil
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyStore
import java.security.SecureRandom
import java.security.cert.CertificateFactory
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicReference
import javax.net.ssl.KeyManagerFactory
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

class RetrofitManager(interceptor: Interceptor) {

    private var retrofit: Retrofit? = null

    init {
        createRetrofit(interceptor)
    }

    fun createRetrofit(interceptor: Interceptor) {
        val okHttpClient = createOkHttpClient(interceptor)

        val gson = GsonUtil.instance.gson

        retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.newThread()))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://ptx.transportdata.tw/")
                .client(okHttpClient)
                .build()
    }

    private fun createOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(20, TimeUnit.SECONDS)

        initSSLSocketFactory(builder)

        return builder.build()
    }


    private fun initSSLSocketFactory(builder: OkHttpClient.Builder) {
        try {
            val keyStorePassword = "fuckPassword".toCharArray()
            val resources = MyApplication.instance!!.resources
            val inputStream = resources.openRawResource(R.raw.ptx)

            val certificateFactory = CertificateFactory.getInstance("X.509")
            val keyStore = KeyStore.getInstance(KeyStore.getDefaultType())
            keyStore.load(null, keyStorePassword)
            keyStore.setCertificateEntry("ptx", certificateFactory.generateCertificate(inputStream))

            inputStream.close()

            val keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm())
            keyManagerFactory.init(keyStore, keyStorePassword)

            val trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
            trustManagerFactory.init(keyStore)

            val sslContext = SSLContext.getInstance("TLSv1.2")
            sslContext.init(keyManagerFactory.keyManagers, trustManagerFactory.trustManagers, SecureRandom())
            sslContext.socketFactory

            val trustManager = trustManagerFactory.trustManagers[0]
            if (trustManager is X509TrustManager) {
                builder.sslSocketFactory(sslContext.socketFactory, trustManager)
            } else {
                builder.sslSocketFactory(sslContext.socketFactory)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun <T> create(service: Class<T>): T {
        return retrofit!!.create(service)
    }

    companion object {

        private val hashMap = ConcurrentHashMap<RetrofitService, AtomicReference<RetrofitManager>>()

        fun getInstance(service: RetrofitService): RetrofitManager {
            var reference = hashMap[service]
            if (reference == null) {
                reference = AtomicReference()
                hashMap[service] = reference
            }

            while (true) {
                var instance = reference.get()
                if (instance != null) {
                    return instance
                }

                instance = RetrofitManager(service.interceptor)
                if (reference.compareAndSet(null, instance)) {
                    return instance
                }
            }
        }
    }

}
