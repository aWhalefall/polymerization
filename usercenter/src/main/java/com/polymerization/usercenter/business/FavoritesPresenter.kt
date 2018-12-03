package com.polymerization.usercenter.business

import com.appcomponent.base.BaseModel
import com.appcomponent.base.SimpleBaseView
import com.appcomponent.base.refactorone.RefactorPresenter

/**
 * Author: yangweichao
 * Date:   2018/12/3 10:29 AM
 * Description: 收藏列表
 */

class FavoritesPresenter(view: SimpleBaseView) : RefactorPresenter<SimpleBaseView>(view) {

    override fun serverResponse(data: Any) {
        mBaseView.showDataSuccess(data)
    }

    override fun requestSuccess(responseJson: String) {

    }

    override fun requestServer(vararg param: Any) {
        mBaseModel.requestToServer(param)
    }

    override fun getModel(): BaseModel {
        return FavoritesModel(this)
    }
}