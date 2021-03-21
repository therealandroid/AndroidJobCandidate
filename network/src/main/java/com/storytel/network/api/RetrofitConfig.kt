package com.storytel.network.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

 fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com").client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

 fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient
        .Builder()
        .connectTimeout(3, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor())
        .build()
}

 fun providePostsService(retrofit: Retrofit): PostService = retrofit.create(PostService::class.java)