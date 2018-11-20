package com.polymerization.core.retrofit.convert

import android.app.Activity
import com.polymerization.core.utils.DialogUtils
import io.reactivex.ObservableTransformer
import java.lang.ref.WeakReference

/**
 * Author: yangweichao
 * Date:   2018/11/20 2:05 PM
 * Description: 处理网络请求loading
 *
 * ObservableTransformer, A 装饰模式--{A}
 */

class RxLoading {

    /**
     * showDialog 显示隐藏 带tips的
     */
    fun <T> applyProgressBar(context: Activity, msgTips: String): ObservableTransformer<T, T> {
        val activityWeakReference = WeakReference(context)
        //showDialog
        DialogUtils.showDialog(activityWeakReference.get(),msgTips)

        return ObservableTransformer { upstream ->
            upstream.doOnSubscribe {

            }.doOnTerminate {
                if (activityWeakReference.get() != null && !activityWeakReference.get()!!.isFinishing) {
                    DialogUtils.dimisssDialog()
                }
            }
        }
    }

    /**
     * 不带的tips的
     */
    fun <T> applyProgressBar(context: Activity): ObservableTransformer<T, T> {
        return applyProgressBar(context, "")
    }

}
