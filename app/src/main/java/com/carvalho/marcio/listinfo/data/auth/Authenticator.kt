package com.carvalho.marcio.listinfo.data.auth

import android.service.autofill.UserData
import okhttp3.Interceptor
import okhttp3.Response

object Authenticator : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        authToken?.let {
            request = request.newBuilder()
                .addHeader("Authorization", "Bearer $it")
                .build()
        }
        return chain.proceed(request)
    }

    var userData: UserData? = null
    var authToken: String? = null
}