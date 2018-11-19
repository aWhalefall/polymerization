package com.appcomponent.base.action

import android.app.Activity
import android.content.Context
import android.view.View

/**
 * Activity 与 Fragment 共同的行为模板
 */
interface BaseActionTemplate {

    /**
     * 点击次数限制
     */
    fun clickLimit(view: View,context: Activity)

    fun clickLimitMillisecond(context: Activity, view: View, millisecond: Long)
}