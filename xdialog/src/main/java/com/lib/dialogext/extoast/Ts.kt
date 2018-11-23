package com.lib.dialogext.extoast

import android.content.Context

/**
 * Author: yangweichao
 * Date:   2018/11/23 4:21 PM
 * Description: Toaste 门面
 * 写法有问题，是否要替换为java 静态类
 */

object Ts {

    private lateinit var templateInvoker: TsTemplateImp

    fun initTs(context: Context) {
        templateInvoker = TsTemplateImp(context)
    }

    @JvmStatic
    fun show(msg: Any) {
        templateInvoker.show(msg)
    }

}