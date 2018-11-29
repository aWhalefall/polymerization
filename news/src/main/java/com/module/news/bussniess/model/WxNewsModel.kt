package com.module.news.bussniess.model

import com.appcomponent.base.AbsBaseModel
import com.appcomponent.base.refactorone.AbsRefactor1BaseModel
import com.appcomponent.utils.ResponseTransformer
import com.appcomponent.utils.RxJavaUtils
import com.appcomponent.utils.RxLoading
import com.appcomponent.utils.StackManager
import com.module.news.bussniess.Articlesevice
import com.module.news.bussniess.NewPresenter
import com.polymerization.core.okhttp.NetUtils
import com.safframework.log.L
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer


/**
 * // TODO: 2018/11/22  将参数传递给父类，写法繁琐
 */
class WxNewsModel(basePresenter: NewPresenter) : AbsRefactor1BaseModel<NewPresenter>(basePresenter) {

    private var method: Int = 0

    // TODO: 2018/11/23fase
    override fun requestToServer(param: Any) {
        val args = param as Array<*>
        NetUtils.creatRequest(Articlesevice::class.java).getArticle(args[0].toString())
                .compose(RxJavaUtils.observableToMain())
                .compose(ResponseTransformer.handleResult())
                .subscribe(Consumer<ArticleBo> {
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


    override fun requestToServer() {
        NetUtils.creatRequest(Articlesevice::class.java).getBanner()
                .compose(RxJavaUtils.observableToMain())
                .compose(ResponseTransformer.handleResult())
                .subscribe(Consumer<List<BannerBo>> {
                    basePresenter.ServerResponse(it)
                }, Consumer<Throwable> {
                    L.d(it.message)
                    // basePresenter.requestError(it.localizedMessage)
                }, Action {
                    L.d("网络请求完毕")
                }, Consumer {
                    addDisposable(it)
                })
    }

    override fun setRequestType(method: Int) {
        this.method = method
    }

    fun addFavorite(param: Any) {
        val args = param as Array<*>
        NetUtils.creatRequest(Articlesevice::class.java).addFavorite(args[0].toString())
                .compose(RxJavaUtils.observableToMain())
                .compose(ResponseTransformer.handleResult())
                .compose(RxLoading.applyProgressBar(StackManager.currentActivity()))
                .subscribe(Consumer<Any> {
                    basePresenter.addFavoriteSuccess(it)
                }, Consumer<Throwable> {
                    L.d("error")
                }, Action {
                    L.d("网络请求完毕")
                }, Consumer {
                    addDisposable(it)
                })
    }

}
