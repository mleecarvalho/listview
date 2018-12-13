package com.carvalho.marcio.listinfo.di

import com.carvalho.marcio.listinfo.BuildConfig
import com.carvalho.marcio.listinfo.data.factory.OkHttpClientFactory
import com.carvalho.marcio.listinfo.data.factory.RetrofitFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val networkModule = module {
    single { OkHttpClientFactory.build(androidContext()) }
    single { RetrofitFactory.build(BuildConfig.SERVER_URL, get(), get()) }
}