package com.appcomponent.base

interface SimpleBaseView : BaseView{
    override fun showLoading(isShow: Boolean) {
    }

    override fun showDataSuccess(msg: String) {
    }

    override fun showDataFailure(msg: String) {
    }

    override fun showDataSuccess(obj: Any) {
    }
}