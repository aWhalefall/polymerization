package com.polymerization.core.retrofit.convert

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.functions.Function
import com.polymerization.core.retrofit.Exception.LocalException
import com.polymerization.core.retrofit.Exception.ServiceException
import com.polymerization.core.retrofit.respond.Response

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
            val code = tResponse.showapi_res_code
            val message = tResponse.showapi_res_error
            return if (code == 0) {
                Observable.just(tResponse.showapi_res_body!!)
            } else {
                Observable.error(ServiceException(code, message))
            }
        }
    }


