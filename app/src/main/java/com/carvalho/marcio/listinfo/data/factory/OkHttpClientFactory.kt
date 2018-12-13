package com.carvalho.marcio.listinfo.data.factory

import android.content.Context
import com.carvalho.marcio.listinfo.BuildConfig
import com.carvalho.marcio.listinfo.data.auth.Authenticator
import com.carvalho.marcio.listinfo.data.http.UploadProgressRequestBody
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.BufferedInputStream
import java.security.KeyStore
import java.security.cert.CertificateFactory
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

object OkHttpClientFactory {

    fun build(context: Context): OkHttpClient {
        val builder = OkHttpClient.Builder()

        setupClient(context, builder)

        builder.addInterceptor(Authenticator)
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
        }

        // TODO: You can handlink Certificate Pinner Here

        builder.readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)

        // Progress listener
        builder.addInterceptor {
            val originalRequest = it.request()
            if (originalRequest.body() == null) {
                return@addInterceptor it.proceed(originalRequest)
            }

            val progressRequest = originalRequest.newBuilder()
                .method(originalRequest.method(), UploadProgressRequestBody(originalRequest.body()))
                .build()

            return@addInterceptor it.proceed(progressRequest)
        }

        return builder.build()
    }

    private fun setupClient(context: Context, builder: OkHttpClient.Builder) {
        val keyStore = KeyStore.getInstance(KeyStore.getDefaultType())
        keyStore.load(null, null)
        val certInputStream = context.assets.open("intermediaria.pem")
        val bis = BufferedInputStream(certInputStream)
        val certificateFactory = CertificateFactory.getInstance("X.509")
        while (bis.available() > 0) {
            val cert = certificateFactory.generateCertificate(bis)
            keyStore.setCertificateEntry("app.dev.core.aws.infra", cert)
        }
        val trustManagerFactory =
            TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
        trustManagerFactory.init(keyStore)
        val trustManagers = trustManagerFactory.trustManagers
        val sslContext = SSLContext.getInstance("TLS")
        sslContext.init(null, trustManagers, null)
        builder.sslSocketFactory(sslContext.socketFactory, trustManagers[0] as X509TrustManager)
    }
}
