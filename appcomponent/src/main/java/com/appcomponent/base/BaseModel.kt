package com.appcomponent.base

/**
 * Author: yangweichao
 * Date:   2018/11/14 3:05 PM
 * Description: model
 * 1.请求网络
 * 2.数据存储。sqlite，sp，txt
 * 3.结果通知presenter 层
 */


interface BaseModel {

    fun requestToServer()

    /**
     * 请求接受任意多的类型
     */
    fun requestToServer(args: Any)


    fun setRequestType(method: Int)

    fun cancelRequest()
}