package com.appcomponent.base.action

/**
 * Activity 与 Fragment 共同的行为模板
 * 1.8 接口默认实现
 */
interface BaseTemplate {
    fun initParameter(){}
    fun initLayout(){}
    fun initView(){}
    fun initListener(){}
    fun initValue(){}
}