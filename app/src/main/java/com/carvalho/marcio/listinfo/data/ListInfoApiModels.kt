package com.carvalho.marcio.listinfo.data;

import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;

@JsonClass(generateAdapter = true)
data class LogInRequest(
    @Json(name = "username") val cpf: String,
    @Json(name = "password") val password: String
)

@JsonClass(generateAdapter = true)
data class ApiError(
    @Json(name = "type") val type: String? = null,
    @Json(name = "message") val message: String? = null,
    @Json(name = "error") val error: String? = null, val code: Int = 0
)

@JsonClass(generateAdapter = true)
data class UserData(
    @Json(name = "name") val name: String,
    @Json(name = "product_request_id") val productId: String?
)
