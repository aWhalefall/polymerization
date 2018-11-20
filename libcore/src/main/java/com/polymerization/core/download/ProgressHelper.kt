package com.polymerization.core.download

import okhttp3.OkHttpClient

/**
 * Author: yangweichao
 * Date:   2018/11/20 5:03 PM
 * Description: 独立于普通请求
 */


class ProgressHelper {

    var retrofitBean: RetrofitBean = RetrofitBean()
    lateinit var progressHandler: ProgressHandler //线程切换handler
    lateinit var builder: OkHttpClient.Builder

    fun addProgress(builder: OkHttpClient.Builder) {

        this.builder = builder
        if (builder == null) {
            this.builder = OkHttpClient.Builder()
            // TODO: 2018/11/20 定义超时时间
        }
        /**
         * 定义的回调函数
         */
        val progressListener: ProgressListener = object : ProgressListener {
            override fun progress(total: Long, progress: Long, isComplete: Boolean) {
                if (progressHandler == null) return
                retrofitBean.totalCount = total
                retrofitBean.currentLenth = progress
                retrofitBean.incompleteResults = isComplete
                progressHandler.sendMessage(retrofitBean)
            }
        }
        //添加拦截器，自定义ResponseBody，添加下载进度
        builder.addNetworkInterceptor { chain ->
            var response = chain.proceed(chain.request())
            response.newBuilder().body(ProgressRespondBody(response.body()!!, progressListener)).build()
        }
    }


}