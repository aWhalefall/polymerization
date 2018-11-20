package com.polymerization.core.download

import android.os.Handler
import android.os.Looper
import android.os.Message

abstract class ProgressHandler {

     abstract fun sendMessage(retrofitBean: RetrofitBean)

    protected abstract fun handleMessage(msg: Message)

    protected abstract fun progress(total: Long, progress: Long, isComplete: Boolean)

     inner class ResponseHandler(progressHandler: ProgressHandler, mLooper: Looper) : Handler(mLooper) {

        private val mProgressHandler: ProgressHandler = progressHandler;
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            mProgressHandler.handleMessage(msg)
        }

    }

}
