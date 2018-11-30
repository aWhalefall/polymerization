package com.polymerization.video.bussiness

import com.appcomponent.base.BaseModel
import com.appcomponent.base.refactorone.RefactorPresenter


class VideoPresenter(mview: VideoView) : RefactorPresenter<VideoView>(mview) {

    override fun serverResponse(data: Any) {
        mBaseView.showDataSuccess(data)
    }


    override fun requestServer(vararg param: Any) {
       mBaseModel.requestToServer(param)
    }

    override fun requestSuccess(responseJson: String) {
    }

    override fun getModel(): BaseModel {
        return VideoModel(this)
    }


}