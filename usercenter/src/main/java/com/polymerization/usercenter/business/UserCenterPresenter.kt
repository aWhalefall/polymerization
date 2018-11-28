package com.polymerization.usercenter.business

import com.appcomponent.base.BaseModel
import com.appcomponent.base.refactorone.RefactorPresenter1

/**
 * Author: yangweichao
 * Date:   2018/11/28 3:41 PM
 * Description: 用户中心
 */


class UserCenterPresenter(view: UserCenterView) : RefactorPresenter1<UserCenterView>(view) {

    override fun serverResponse(data: Any) {
        mBaseView.showDataSuccess(data)
    }

    override fun requestServer(vararg param: Any) {
        mBaseModel.requestToServer()
    }

    override fun requestSuccess(responseJson: String) {
    }

    override fun getModel(): BaseModel {
        return UserCenterModel(this)
    }
}