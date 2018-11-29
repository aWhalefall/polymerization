package com.polymerization.usercenter.business

import com.appcomponent.base.refactorone.AbsRefactor1BaseModel
import com.appcomponent.utils.ResponseTransformer
import com.appcomponent.utils.RxJavaUtils
import com.polymerization.core.okhttp.NetUtils
import com.safframework.log.L
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer

class UserCenterModel(presenter: UserCenterPresenter) : AbsRefactor1BaseModel<UserCenterPresenter>(presenter) {

    override fun requestToServer() {
        NetUtils.creatRequest(UserRequestService::class.java).exit()
                .compose(RxJavaUtils.observableToMain()).
                        compose(ResponseTransformer.handleResult())
                .subscribe(
                        Consumer<Any> {
                            basePresenter.serverResponse(it)
                        }, Consumer<Throwable> {
                    L.d(it.message)
                }, Action { }, Consumer<Disposable> { addDisposable(it) }
                )
    }

    override fun requestToServer(args: Any) {
    }

}
