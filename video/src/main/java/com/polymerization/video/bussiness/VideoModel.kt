package com.polymerization.video.bussiness

import com.appcomponent.base.refactorone.AbsRefactorBaseModel
import com.appcomponent.utils.ResponseTransformer
import com.appcomponent.utils.RxJavaUtils
import com.polymerization.core.okhttp.NetUtils
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer


class VideoModel(mBasePresenter: VideoPresenter) : AbsRefactorBaseModel<VideoPresenter>(mBasePresenter) {


    override fun requestToServer() {

    }

    override fun requestToServer(args: Any) {
        val params = args as Array<*>
        NetUtils.creatRequest(VideoRequest::class.java).getProjectLists(params[0].toString())
                .compose(RxJavaUtils.observableToMain()).compose(ResponseTransformer.handleResult()).subscribe(
                        Consumer {
                            basePresenter.serverResponse(it)
                        }, Consumer<Throwable> { }, Action { }, Consumer<Disposable> { addDisposable(it) }
                )
    }

}