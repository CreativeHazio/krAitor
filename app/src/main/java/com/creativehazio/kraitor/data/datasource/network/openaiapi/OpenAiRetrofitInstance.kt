package com.creativehazio.kraitor.data.datasource.network.openaiapi

import com.creativehazio.kraitor.R
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class OpenAiRetrofitInstance {

    companion object {
        private const val BASE_URL = "https://api.openai.com/v1/"
        //TODO: How to securely store api keys
        private const val OPEN_AI_KEY = "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"

        private val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        private val okHttpClient = OkHttpClient.Builder().apply {

            this.addInterceptor {
                val originalRequest = it.request()
                return@addInterceptor it.proceed(
                    originalRequest.newBuilder()
                        .header("Content-Type", "application/json")
                        .header("Authorization", "Bearer $OPEN_AI_KEY")
                        .build()
                )
            }.addInterceptor(httpLoggingInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)

        }.build()

        fun getRetrofitInstance() : Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .client(okHttpClient)
                .build()
        }
    }
}