package com.polymerization.core.download

import android.os.Looper
import android.os.Message

/**
 * Author: yangweichao
 * Date:   2018/11/20 5:37 PM
 * Description:  todo https://blog.csdn.net/ljd2038/article/details/51189334 需要测试框架是否好用
 */


abstract class DownProgressHandler : ProgressHandler() {


    private val DOWNLOAD_PROGRESS = 1
    var mHandler: ResponseHandler = ResponseHandler(this, Looper.getMainLooper())

    override fun sendMessage(retrofitBean: RetrofitBean) {
        mHandler.obtainMessage(DOWNLOAD_PROGRESS, retrofitBean).sendToTarget()

    }

    override fun handleMessage(msg: Message) {
        when (msg.what) {
            DOWNLOAD_PROGRESS -> {
                with(msg.obj as RetrofitBean) {
                    progress(totalCount, currentLenth, incompleteResults)
                }
            }
        }
    }

    override fun progress(total: Long, progress: Long, isComplete: Boolean) {

    }
}
