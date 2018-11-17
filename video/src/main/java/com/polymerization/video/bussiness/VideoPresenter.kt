package com.polymerization.video.bussiness

import com.appcomponent.base.AbstractBasePresenter
import com.appcomponent.base.BaseModel
import com.polymerization.video.model.FlowerBean


class VideoPresenter(mBaseView: VideoView, clazz: Class<FlowerBean>):AbstractBasePresenter<Any,FlowerBean>(mBaseView,clazz) {




    override fun serverResponse(data: FlowerBean) {
       mBaseView.showDataSuccess(data)
    }

    override fun requestServer(vararg param: Any) {
       mBaseModel.requestToServer(param)
    }

    override fun requestSuccess(responseJson: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getModel(): BaseModel {
        return VideoModel(this)
    }


}