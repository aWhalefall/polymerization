package com.appcompat.loginmodule.logic

import com.appcomponent.base.BaseModel
import com.appcomponent.base.SimpleBaseView
import com.appcomponent.base.refactorone.RefactorPresenter

class RegisterPresenter(view: SimpleBaseView) : RefactorPresenter<SimpleBaseView>(view) {

    override fun serverResponse(data: Any) {
        mBaseView.showDataSuccess(data)
    }

    override fun requestSuccess(responseJson: String) {
    }

    override fun getModel(): BaseModel {
        return RegisterModel(this)
    }
}