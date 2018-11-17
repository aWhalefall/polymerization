package com.module.news.bussniess

import com.appcomponent.base.AbstractBasePresenter
import com.appcomponent.base.BaseModel
import com.appcomponent.base.BaseView
import com.module.news.bussniess.model.WxNewsModel
import com.polymerization.core.bean.JavaBean

class NewPresenter(mBaseView: BaseView, clazz: Class<JavaBean>) :
        AbstractBasePresenter<Any, JavaBean>(mBaseView, clazz) {



    override fun requestSuccess(responseJson: String) {
    }


    override fun serverResponse(data: JavaBean) {
        mBaseView.showDataSuccess(data)
    }

    override fun requestServer(vararg args: Any) {
       mBaseModel.requestToServer(args)
    }


    override fun getModel(): BaseModel {
        return WxNewsModel(this)
    }

}
