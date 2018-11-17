package com.polymerization.video.bussiness

import com.appcomponent.base.BaseView

/**
 * Author: yangweichao
 * Date:   2018/11/17 11:22 AM
 * Description: 如何区分一个接口，或者抽象类是否被实现，判断是否有方法体
 */


interface VideoView:BaseView {
    override fun showLoading(isShow: Boolean) {

    }

    override fun showDataSuccess(msg: String) {
    }

    override fun showDataFailure(msg: String) {
    }

    override fun showDataSuccess(obj: Any) {
    }
}