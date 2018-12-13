package com.carvalho.marcio.listinfo.data.http

import java.io.OutputStream

class ProgressOutputStream(private val stream: OutputStream?, private val total: Long) : OutputStream() {

    private var totalWritten = 0L

    override fun write(b: ByteArray, off: Int, len: Int) {
        stream?.write(b, off, len)
        if (total < 0) {
            ProgressListener.update(-1, -1)
            return
        }

        totalWritten += if (len < b.size) {
            len
        } else {
            b.size
        }
        ProgressListener.update(totalWritten, total)
    }

    override fun write(b: Int) {
        stream?.write(b)
        if (total < 0) {
            ProgressListener.update(-1, -1)
            return
        }

        totalWritten++
        ProgressListener.update(totalWritten, total)
    }

    override fun close() {
        stream?.close()
    }

    override fun flush() {
        stream?.flush()
    }
}
