package com.carvalho.marcio.listinfo.data.http

import okhttp3.RequestBody
import okio.BufferedSink
import okio.buffer
import okio.sink

class UploadProgressRequestBody(private val requestBody: RequestBody?) : RequestBody() {

    override fun contentType() = requestBody?.contentType()

    override fun contentLength() = requestBody?.contentLength() ?: 0

    override fun writeTo(sink: BufferedSink) {
        val progressOutputStream = ProgressOutputStream(sink.outputStream(), contentLength())
        val progressSink = progressOutputStream.sink().buffer()
        requestBody?.writeTo(progressSink)
        progressSink.flush()
    }

}
