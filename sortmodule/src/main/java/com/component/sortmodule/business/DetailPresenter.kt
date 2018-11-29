package com.component.sortmodule.business

import com.appcomponent.base.BaseModel
import com.appcomponent.base.BaseView
import com.appcomponent.base.refactorone.RefactorPresenter1

class DetailPresenter(view: BaseView) : RefactorPresenter1<BaseView>(view) {

    override fun serverResponse(data: Any) {
        mBaseView.showDataSuccess(data)
    }

    override fun requestSuccess(responseJson: String) {

    }

    override fun getModel(): BaseModel {
        return DetailModel(this)
    }
}