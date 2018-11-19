package com.appcomponent.widget

import android.annotation.SuppressLint
import android.support.annotation.IdRes

@SuppressLint("SupportAnnotationUsage")
internal interface IActionBarResIdProvider {

    @IdRes
    fun getActionBarId():Int


    @IdRes
    fun getActionBarTitleId():Int

    @IdRes
    fun getActionBarContainerId():Int

    @IdRes
    fun getActionBarIconId():Int

    @IdRes
    fun getActionBarActionId():Int
}
