package com.module.news.bussniess.model

import com.appcomponent.base.AbsBaseModel
import com.appcomponent.utils.ResponseTransformer
import com.appcomponent.utils.RxJavaUtils
import com.appcomponent.utils.RxLoading
import com.appcomponent.utils.StackManager
import com.module.news.bussniess.NewPresenter
import com.polymerization.core.bean.JavaBean
import com.polymerization.core.okhttp.NetUtils
import com.safframework.log.L
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer


/**
 * // TODO: 2018/11/22  将参数传递给父类，写法繁琐
 *
 */
class WxNewsModel(basePresenter: NewPresenter) : AbsBaseModel<NewPresenter>(basePresenter) {

    // TODO: 2018/11/23fase
    override fun requestToServer(param: Any) {
        val args = param as Array<*>

        NetUtils.getRequest()
                .getWeatherByAddress(
                        args[0].toString(),
                        args[1].toString())
                .compose(RxJavaUtils.observableToMain())
                .compose(ResponseTransformer.handleResult())
                .subscribe(Consumer<JavaBean> {
                    basePresenter.serverResponse(it)
                    L.d(it.toString())
                }, Consumer<Throwable> {
                    L.d(it.message)
                    // basePresenter.requestError(it.localizedMessage)
                }, Action {
                    L.d("网络请求完毕")
                }, Consumer {
                    addDisposable(it)
                })


    }

    private var method: Int = 0


    override fun requestToServer() {

    }

    override fun setRequestType(method: Int) {
        this.method = method
    }

}
