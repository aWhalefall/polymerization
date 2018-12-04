package com.polymerization.usercenter.business

import com.appcomponent.base.refactorone.AbsRefactorBaseModel
import com.appcomponent.utils.ResponseTransformer
import com.appcomponent.utils.RxJavaUtils
import com.appcomponent.utils.RxLoading
import com.appcomponent.utils.StackManager
import com.polymerization.core.okhttp.NetUtils
import com.polymerization.usercenter.model.ArticleBo
import com.safframework.log.L
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer

class FavoritesModel(presenter: FavoritesPresenter) : AbsRefactorBaseModel<FavoritesPresenter>(presenter) {

    override fun requestToServer() {


    }

    override fun requestToServer(param: Any) {
        val args = param as Array<*>
        NetUtils.creatRequest(UserRequestService::class.java).getFavoriteList(args[0].toString())
                .compose(RxJavaUtils.observableToMain())
                .compose(ResponseTransformer.handleResult())
                .compose(RxLoading.applyProgressBar(StackManager.currentActivity()))
                .subscribe(Consumer<ArticleBo> {
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