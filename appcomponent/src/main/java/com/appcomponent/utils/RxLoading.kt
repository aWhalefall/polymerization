package com.appcomponent.utils

import android.support.v7.app.AppCompatActivity
import com.lib.dialogext.ExtDialogUtils
import io.reactivex.ObservableTransformer
import java.lang.ref.WeakReference

/**
 * Author: yangweichao
 * Date:   2018/11/20 2:05 PM
 * Description: 处理网络请求loading
 *
 * ObservableTransformer, A 装饰模式--{A}
 */

object RxLoading {


    /**
     * showDialog 显示隐藏 带tips的
     */
    @JvmStatic
    fun <T> applyProgressBar(context: AppCompatActivity, msgTips: String): ObservableTransformer<T, T> {
        val activityWeakReference = WeakReference(context)
        //showDialog
        var dia = ExtDialogUtils.showPhoneDialog(activityWeakReference.get(), msgTips)

        return ObservableTransformer { upstream ->
            upstream.doOnSubscribe {
            }.doOnTerminate {
                if (activityWeakReference.get() != null && !activityWeakReference.get()!!.isFinishing) {
                    //DialogUtils.dimisssDialog()
                    dia.dismiss()

                }
            }
        }
    }

    /**
     * 不带的tips的
     */
    @JvmStatic
    fun <T> applyProgressBar(context: AppCompatActivity): ObservableTransformer<T, T> {
        return applyProgressBar(context, "")
    }

}
