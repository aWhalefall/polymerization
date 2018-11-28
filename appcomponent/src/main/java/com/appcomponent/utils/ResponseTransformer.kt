package com.appcomponent.utils

import com.appcomponent.router.ArouterHelper
import com.appcomponent.router.PathConfig
import com.polymerization.core.retrofit.Exception.LocalException
import com.polymerization.core.retrofit.Exception.ServiceException
import com.polymerization.core.retrofit.respond.Response
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.functions.Function

/**
 * Author: yangweichao
 * Date:   2018/11/13 2:35 PM
 * Description:  可以将一个被观察者类型转换为另一个被观察者类型
 */


//转换器
 object ResponseTransformer {

    @JvmStatic
    fun <T> handleResult(): ObservableTransformer<Response<T>, T> {
        return  ObservableTransformer {
            upstream -> upstream
                .onErrorResumeNext(ErrorResumeFunction()).flatMap(ResponseFunction()); }  }
    }


    /**
     * 非服务器产生的异常，比如本地无无网络请求，Json数据解析错误等等。
     *
     * @param <T>
    </T> */
    private class ErrorResumeFunction<T> : Function<Throwable, ObservableSource<out Response<T>>> {

        @Throws(Exception::class)
        override fun apply(throwable: Throwable): ObservableSource<out Response<T>> {
            return Observable.error(LocalException.handleException(throwable))
        }
    }

    /**
     * 服务其返回的数据解析
     * 正常服务器返回数据和服务器可能返回的exception
     *
     * @param <T>
    </T> */
    private class ResponseFunction<T> : Function<Response<T>, ObservableSource<T>> {

        @Throws(Exception::class)
        override fun apply(tResponse: Response<T>): ObservableSource<T> {
            val code = tResponse.errorCode
            val message = tResponse.errorMsg
            return if (code == 0) {
                Observable.just(tResponse.data!!)
            } else {
                detalCode(code, message)
                Observable.error(ServiceException(code, message))
            }
        }

        /**
         * 自定义处理code
         */
        private fun detalCode(code: Int, message: String?) {
            when (code) {
                -1001 -> { //未登录或者登录态失效
                    ArouterHelper.startActivity(PathConfig.LOGIN_ACTIVITY)
                }
            }
        }
    }


