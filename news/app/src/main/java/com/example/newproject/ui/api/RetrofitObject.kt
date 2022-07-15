package com.example.newproject.ui.api

import com.example.newproject.App
import com.example.newproject.utils.SharedPreference
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.security.cert.CertificateException
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


object RetrofitObject {
    var gson = GsonBuilder()
        .setLenient()
        .create()

    var authToken = SharedPreference(App.instance.applicationContext).userToken
    private val retrofit by lazy {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        var client = getUnsafeOkHttpClient().addInterceptor { chain ->
            chain.proceed(chain.request().newBuilder().also {
                if (authToken != null) {
                    it.addHeader("Authorization", "Bearer $authToken").build()
                }
            }.build())
        }
            .addInterceptor(logging)
            .build()

        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson)) // заполнение через json
            .client(client)
            .build()
    }


//    private var logHangler: ((String) -> (Unit))? = null
//
//    fun getClientBuilderWithAuth(): OkHttpClient.Builder {
//
//        val builder = OkHttpClient.Builder()
//        val logHandler = logHangler
//        if (logHandler != null)
//            builder.addInterceptor {
//                val request = it.request()
//                logHandler("${request.method} ${request.url}")
//                it.proceed(request)
//
//            }
//
//        return builder
//
//    }

    private fun getUnsafeOkHttpClient(): OkHttpClient.Builder {
        try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(
                    chain: Array<java.security.cert.X509Certificate>,
                    authType: String
                ) {
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(
                    chain: Array<java.security.cert.X509Certificate>,
                    authType: String
                ) {
                }

                override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                    return arrayOf()
                }
            })

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())
            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory

            return OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
                .hostnameVerifier { _, _ -> true }
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    val api by lazy {
        retrofit.create(Api::class.java)
    }
}