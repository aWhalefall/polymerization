package com.appcomponent.base.refactorone

import com.appcomponent.base.BaseModel
import com.appcomponent.widget.CompositeDisposableManager
import io.reactivex.disposables.Disposable

/**
 * Author: yangweichao
 * Date:   2018/11/14 3:05 PM
 * Description: model
 * 1.请求网络
 * 2.数据存储。sqlite，sp，txt
 * 3.结果通知presenter 层
 */


abstract class AbsRefactorBaseModel<T : SimpleBasePresenter>(basePresenter: T) : BaseModel {

    var basePresenter: T = basePresenter

    /**
     * 在订阅事件中进行Disposable 收集；
     */
    protected fun addDisposable(a: Disposable) {
        CompositeDisposableManager.add(a)
    }

    override fun cancelRequest() {
        CompositeDisposableManager.removeAll()
    }

    override fun setRequestType(method: Int) {

    }

}