package com.appcomponent.base

import java.util.*


abstract class AbstractBasePresenter<Params, Data> : BasePresenter {


    val TAG: String = this.javaClass.simpleName
    var mBaseModel: BaseModel
    var mBaseView: BaseView
    var params: Params? = null
    var clazz: Class<Data>

    constructor(mBaseView: BaseView, clazz: Class<Data>) {
        this.mBaseView = mBaseView
        this.clazz = clazz
        mBaseModel = getModel()
    }

    /**
     * Author: yangweichao
     * Date:   2018/11/14 3:55 PM
     * Description: 处理mode 层返回的结果->回调给view层
     */


    abstract fun serverResponse(data: Data)

    override fun cancelRequest() {

        mBaseModel.cancelRequest()
    }


    override fun requestError(code: Int, errorMsg: String) {
        mBaseView.showDataFailure(code.toString() + ":" + errorMsg)
    }

    override fun getModel(): BaseModel {
        return mBaseModel
    }

    override fun getParams(): HashMap<String, String> {
        return HashMap()
    }
}