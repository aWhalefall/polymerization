package com.appcomponent.utils

import io.reactivex.FlowableTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
/**
 * Author: yangweichao
 * Date:   2018/11/13 2:49 PM
 * Description: 配合compose 使用
 * 能够从数据流中得到原始的被观察者，当创建被观察者时候，compose操作符会立即执行
 */


object RxJavaUtils {

    /**
     * 处理网络请求返回线程切换 不支持背压
     */
    @JvmStatic
    fun <T> observableToMain():ObservableTransformer<T,T>{
        return ObservableTransformer {
            upstream -> upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())}
    }

    /**
     * 处理网络请求返回线程切换 支持背压
     */
    @JvmStatic
    fun <T> flowableToMain(): FlowableTransformer<T, T> {
        return FlowableTransformer {
            upstream -> upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())}
    }


}