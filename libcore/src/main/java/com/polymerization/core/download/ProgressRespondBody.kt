package com.polymerization.core.download

import okhttp3.MediaType
import okhttp3.ResponseBody
import okio.*


/**
 * Author: yangweichao
 * Date:   2018/11/20 4:05 PM
 * Description: 如何知道进度，网络链接，服务端向客户端下发数据时候才可以知道，
 * 进度是数据总长度，已经下发长度的映射
 *
 * 需要在初始化okHttp 3的地方增加网络拦截器
 */

class ProgressRespondBody(responseBody: ResponseBody, progressListener: ProgressListener) : ResponseBody() {


    var responseBody: ResponseBody = responseBody
    var progressListener: ProgressListener = progressListener
    private var bufferedSource: BufferedSource? = null


    override fun contentLength(): Long {
        return responseBody.contentLength()
    }

    override fun contentType(): MediaType? {
        return responseBody.contentType()
    }

    override fun source(): BufferedSource? {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(progressSource(responseBody.source()))
        }
        return bufferedSource
    }

    private fun progressSource(source: BufferedSource?): Source {
        return object : ForwardingSource(source) {

            // 当前读取字节数
            var currentReadBytes = 0L
            // 每次读取的长度
            var perReadSize = 0L
            // 总字节长度，避免多次调用contentLength()方法
            var contentLength = 0L
            // 下载完成
            var isDone = false

            override fun read(sink: Buffer, byteCount: Long): Long {
                perReadSize = super.read(sink, byteCount)
                if (contentLength == 0L) {
                    contentLength = contentLength()
                }
                currentReadBytes += if (perReadSize > 0L) perReadSize else 0

                if (null != progressListener)
                    progressListener.progress(contentLength, currentReadBytes, currentReadBytes == contentLength)
                return perReadSize
            }


        }
    }


}

