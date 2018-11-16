package com.module.news.bussniess.model

import com.appcomponent.base.BaseModel
import com.appcomponent.utils.ResponseTransformer
import com.appcomponent.utils.RxJavaUtils
import com.module.news.bussniess.NewPresenter
import com.polymerization.core.bean.JavaBean
import com.polymerization.core.okhttp.NetWorkManager
import com.safframework.log.L
import io.reactivex.functions.Consumer

class WxNewsModel(basePresenter: NewPresenter) : BaseModel {


    var newPresenter: NewPresenter = basePresenter

    override fun requestToServer(param: Any) {
        val args = param as Array<*>
        NetWorkManager.getRequest()
                .getWeatherByAddress(args[0].toString(), args[1].toString(), args[2].toString())
                .compose(ResponseTransformer.handleResult())
                .compose(RxJavaUtils.observableToMain())
                .subscribe(Consumer<JavaBean> {
                    newPresenter.serverResponse(it)
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