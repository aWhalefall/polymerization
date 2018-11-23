package com.appcomponent.base

/**
 * Author: yangweichao
 * Date:   2018/11/14 2:46 PM
 * Description: 基类View
 */


interface BaseView {


    fun showLoading(isShow:Boolean)

    fun showDataSuccess(msg: String)

    fun showDataFailure(msg: String)

    fun showDataSuccess(obj: Any)
}