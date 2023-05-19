package com.ibrahimcanerdogan.hilttutorialapp.data.remote

import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import com.ibrahimcanerdogan.hilttutorialapp.BuildConfig

object NetworkBuilder {

    private const val NETWORK_CALL_TIMEOUT = 60L

    fun create(baseUrl : String, cacheDir : File, cacheSize: Long) : NetworkService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient(cacheDir, cacheSize))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NetworkService::class.java)
    }

    private fun okHttpClient(cacheDir : File, cacheSize: Long) : OkHttpClient {
        return OkHttpClient.Builder()
            .cache(Cache(cacheDir, cacheSize))
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            .readTimeout(NETWORK_CALL_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(NETWORK_CALL_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }
}