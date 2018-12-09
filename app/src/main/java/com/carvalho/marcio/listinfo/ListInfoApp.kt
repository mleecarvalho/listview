package com.carvalho.marcio.listinfo

import android.app.Application
import com.carvalho.marcio.listinfo.data.networkModule
import com.carvalho.marcio.listinfo.di.viewModelModule
import org.koin.android.ext.android.startKoin

open class ListInfoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
        prepareRxJavaErrorHandler()
    }

    private fun setupKoin() {
        startKoin(this, listOf(viewModelModule, networkModule))
    }

    private fun prepareRxJavaErrorHandler() {
        /// TODO: will be implemented soon
    }

}