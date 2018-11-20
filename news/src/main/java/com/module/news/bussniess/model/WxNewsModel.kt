package com.module.news.bussniess.model

import com.appcomponent.base.BaseModel
import com.appcomponent.utils.ResponseTransformer

import com.appcomponent.utils.RxJavaUtils
import com.module.news.bussniess.NewPresenter
import com.polymerization.core.bean.JavaBean
import com.polymerization.core.okhttp.NetWorkManager
import com.safframework.log.L
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function

class WxNewsModel(basePresenter: NewPresenter) : BaseModel {


    var newPresenter: NewPresenter = basePresenter

    override fun requestToServer(param: Any) {
        val args = param as Array<*>

        NetWorkManager.getRequest()
                .getWeatherByAddress(
                        args[0].toString(),
                        args[1].toString())
                .compose(RxJavaUtils.observableToMain())
                .compose(ResponseTransformer.handleResult())
                .subscribe(Consumer<JavaBean> {
                    newPresenter.serverResponse(it)
                    L.d(it.toString())
                }, Consumer<Throwable> {
                    L.d(it.message)
                    // basePresenter.requestError(it.localizedMessage)
                })
    }


    private var method: Int = 0


    override fun requestToServer() {

    }


    override fun setRequestType(method: Int) {
        this.method = method
    }

    override fun cancelRequest() {
        // TODO: 2018/11/16 全局取消 当前网络请求
    }
}

private fun <T> Observable<T>.concatMap(function: Function<String, T>) {

}
