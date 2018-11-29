package com.component.sortmodule.business

import com.appcomponent.base.refactorone.AbsRefactor1BaseModel
import com.appcomponent.utils.ResponseTransformer
import com.appcomponent.utils.RxJavaUtils
import com.component.sortmodule.SortService
import com.polymerization.core.okhttp.NetUtils
import com.safframework.log.L
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer

class SortModel(sortPresenter: SortPresenter) : AbsRefactor1BaseModel<SortPresenter>(sortPresenter) {

    override fun requestToServer() {

    }

    override fun requestToServer(args: Any) {
        NetUtils.creatRequest(SortService::class.java).getTreeList()
                .compose(RxJavaUtils.observableToMain())
                .compose(ResponseTransformer.handleResult())
                .subscribe(Consumer{
                    basePresenter.serverResponse(it)
                }, Consumer<Throwable> {
                    L.d(it.message)
                    // basePresenter.requestError(it.localizedMessage)
                }, Action {
                    L.d("网络请求完毕")
                }, Consumer {
                    addDisposable(it)
                })
    }
}