package com.component.sortmodule.business

import com.appcomponent.base.BaseModel
import com.appcomponent.base.refactorone.RefactorPresenter1

class SortPresenter(view: SortView) : RefactorPresenter1<SortView>(view) {

    override fun serverResponse(data: Any) {
        mBaseView.showDataSuccess(data)
    }

    override fun requestSuccess(responseJson: String) {

    }

    override fun getModel(): BaseModel {
        return SortModel(this)
    }
}