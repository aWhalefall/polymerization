package com.appcomponent.base.refactorone

import com.appcomponent.base.BaseModel
import com.appcomponent.base.BaseView
import java.util.*


/**
 * Author: yangweichao
 * Date:   2018/11/23 11:13 AM
 * Description: 去掉多余的泛型
 */

abstract class RefactorPresenter1<T : BaseView> : SimpleBasePresenter {

    val TAG: String = this.javaClass.simpleName
    var mBaseModel: BaseModel
    var mBaseView: T


    constructor(mBaseView: T) {
        this.mBaseView = mBaseView
        mBaseModel = getModel()
    }

    /**
     * f
     * Description: 处理mode 层返回的结果->回调给view层
     */
    abstract fun serverResponse(data: Any)

    override fun cancelRequest() {
        mBaseModel.cancelRequest()
    }

    override fun requestServer(vararg param: Any) {
        mBaseModel.requestToServer(param)
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