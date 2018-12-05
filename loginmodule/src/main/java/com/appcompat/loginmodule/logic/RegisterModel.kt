package com.appcompat.loginmodule.logic

import com.appcomponent.base.refactorone.AbsRefactorBaseModel
import com.appcomponent.utils.ResponseTransformer
import com.appcomponent.utils.RxJavaUtils
import com.appcomponent.utils.RxLoading
import com.appcomponent.utils.StackManager
import com.polymerization.core.okhttp.NetUtils
import com.safframework.log.L
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer

class RegisterModel(v: RegisterPresenter) : AbsRefactorBaseModel<RegisterPresenter>(v) {
    override fun requestToServer() {

    }

    override fun requestToServer(args: Any) {
        var array = args as Array<*>
        NetUtils.creatRequest(ApiLoginService::class.java).register(array[0].toString(),
                array[1].toString(),
                array[2].toString()).compose(ResponseTransformer.handleResult())
                .compose(RxJavaUtils.observableToMain())
                .compose(RxLoading.applyProgressBar(StackManager.currentActivity()))
                .subscribe(Consumer {
                    basePresenter.serverResponse(it)
                }, Consumer<Throwable> {
                    L.d(it.message)
                }, Action {
                    L.d("网络请求完毕")
                }, Consumer {
                    addDisposable(it)
                })
    }

}
