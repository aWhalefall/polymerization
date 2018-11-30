package com.appcompat.loginmodule.logic

import com.appcomponent.base.refactorone.AbsRefactorBaseModel
import com.appcomponent.utils.ResponseTransformer
import com.appcomponent.utils.RxJavaUtils
import com.appcomponent.utils.RxLoading
import com.appcomponent.utils.StackManager
import com.polymerization.core.okhttp.NetUtils
import com.safframework.log.L
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer

/**
 * Author: yangweichao
 * Date:   2018/11/23 11:30 AM
 * Description: model
 */

class LoginModel(loginPresenter: LoginPresenter) : AbsRefactorBaseModel<LoginPresenter>(loginPresenter) {

    override fun requestToServer() {

    }

    override fun requestToServer(args: Any) {
        var params = args as Array<*>
        NetUtils.creatRequest(ApiLoginService::class.java).login(params[0].toString(), params[1].toString())
                .compose(RxJavaUtils.observableToMain())
                .compose(ResponseTransformer.handleResult())
                .compose(RxLoading.applyProgressBar(StackManager.currentActivity()))
                .subscribe(Consumer {
                    basePresenter.serverResponse(it)
                }, Consumer<Throwable> {
                    L.d(it.message) },
                        Action { }
                        , Consumer<Disposable> { addDisposable(it) })
    }

}