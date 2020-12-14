package com.debin.challengechip.di

import com.debin.challengechip.framework.network.ApiService
import com.debin.challengechip.framework.utils.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


var logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)

var client = OkHttpClient.Builder()
    .addInterceptor(logging)
    .build()

val apiModule = module {
    fun provideApi() : ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(ApiService::class.java)
    }

single { provideApi() }
}