package com.carvalho.marcio.listinfo.data.http

object ProgressListener {

    private val listeners = mutableSetOf<(Long, Long) -> Unit>()

    fun registerListener(callback: (Long, Long) -> Unit) {
        listeners.add(callback)
    }

    fun unregisterListener(callback: (Long, Long) -> Unit) {
        listeners.remove(callback)
    }

    internal fun update(bytesWritten: Long, contentLength: Long) {
        listeners.forEach { it(bytesWritten, contentLength) }
    }
}
