package com.storytel.network

import com.storytel.network.api.provideOkHttpClient
import com.storytel.network.api.providePostsService
import com.storytel.network.api.provideRetrofit
import org.koin.dsl.module

//Dependencies for network module
 val networkModule = module {
    factory { providePostsService(get()) }
    factory { provideOkHttpClient() }
    single { provideRetrofit(get()) }
}
