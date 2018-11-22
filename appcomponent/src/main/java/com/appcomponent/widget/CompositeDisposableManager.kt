package com.appcomponent.widget

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Author: yangweichao
 * Date:   2018/11/22 4:17 PM
 * Description: disposable
 *
 * 1 .方案一
 *
 */
object CompositeDisposableManager {

    var compiler= CompositeDisposable()

    fun add(disposable: Disposable){
        compiler?.add(disposable)
    }


    fun removeAll(){
        compiler?.clear()
    }
}