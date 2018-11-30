package com.appcompat.loginmodule.logic

import com.appcomponent.base.BaseModel
import com.appcomponent.base.refactorone.RefactorPresenter

/**
 * Author: yangweichao
 * Date:   2018/11/23 10:54 AM
 * Description:
 *
 *
 */

class LoginPresenter(view: LoginView) : RefactorPresenter<LoginView>(view) {

    override fun serverResponse(data: Any) {
        mBaseView.showDataSuccess(data)
    }

    override fun requestServer(vararg param: Any) {
        mBaseModel.requestToServer(param)
    }

    override fun requestSuccess(responseJson: String) {

    }

    override fun getModel(): BaseModel {
        return LoginModel(this)
    }


}