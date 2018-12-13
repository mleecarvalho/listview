package com.carvalho.marcio.listinfo.data

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface ListInfoApi {

    @POST("auth/login")
    fun logIn(@Body request: LogInRequest): Single<LogInRequest>
}