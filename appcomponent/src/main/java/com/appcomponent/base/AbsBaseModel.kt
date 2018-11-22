package com.appcomponent.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Author: yangweichao
 * Date:   2018/11/14 3:05 PM
 * Description: model
 * 1.请求网络
 * 2.数据存储。sqlite，sp，txt
 * 3.结果通知presenter 层
 */

// TODO: 2018/11/22   AbstractBasePresenter<*, *>  定义方式太操蛋，首个泛型用法需要重新斟酌
abstract class AbsBaseModel<T : AbstractBasePresenter<*, *>>(basePresenter: T) : BaseModel {

    var basePresenter: T = basePresenter

    /**
     * 复合订阅容器
     */
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()


    /**
     * 在订阅事件中进行Disposable 收集；
     */
    protected fun addDisposable(a: Disposable) {
        compositeDisposable.add(a)
    }

    override fun cancelRequest() {
        compositeDisposable.clear()
    }

}