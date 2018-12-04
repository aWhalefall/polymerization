package com.polymerization.usercenter.business

import com.appcomponent.base.refactorone.AbsRefactorBaseModel
import com.appcomponent.utils.ResponseTransformer
import com.appcomponent.utils.RxJavaUtils
import com.polymerization.core.okhttp.NetUtils
import com.safframework.log.L
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer

class FavoritesModelModel(presenter: TagCloudPresenter) : AbsRefactorBaseModel<TagCloudPresenter>(presenter) {

    override fun requestToServer() {


    }

    override fun requestToServer(param: Any) {
        NetUtils.creatRequest(UserRequestService::class.java).getHotKey()
                .compose(RxJavaUtils.observableToMain())
                .compose(ResponseTransformer.handleResult())
                .subscribe(Consumer {
                    basePresenter.serverResponse(it)
                }, Consumer<Throwable> {
                    L.d(it.message)
                    basePresenter.requestError(-1, it.message.toString())
                }, Action {
                    L.d("网络请求完毕")
                }, Consumer {
                    addDisposable(it)
                })
    }

}