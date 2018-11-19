package com.appcomponent.base.action

import android.app.Activity
import android.view.View
import java.util.*


/**
 * Activity 与 Fragment 共同的行为模板
 */
class BaseActionTemplateImpl : BaseActionTemplate {
    var timer = Timer()

    override fun clickLimitMillisecond(context: Activity, view: View, millisecond: Long) {

        if (view.isClickable) {
            view.isClickable = false
            timer.schedule(object : TimerTask() {
                override fun run() {
                    context.runOnUiThread {
                    }
                }
            }, millisecond)
        }
    }

    override fun clickLimit(view: View, context: Activity) {
        if (view.isClickable) {
            view.isClickable = false
            timer.schedule(object : TimerTask() {
                override fun run() {
                    context.runOnUiThread {
                    }
                }
            })
        }
    }


}

/**
 * 扩展函数中可以，直接调用被扩展函数的方法
 */
private fun Timer.schedule(timerTask: TimerTask) {
    schedule(timerTask, 0)
}


