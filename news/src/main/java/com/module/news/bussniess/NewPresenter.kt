package com.module.news.bussniess

import com.appcomponent.base.BaseModel
import com.appcomponent.base.refactorone.RefactorPresenter
import com.module.news.bussniess.model.BannerBo
import com.module.news.bussniess.model.WxNewsModel

class NewPresenter(mBaseView: NewView) :
        RefactorPresenter<NewView>(mBaseView) {

    override fun serverResponse(data: Any) {
        mBaseView.showDataSuccess(data)
    }


    override fun requestSuccess(responseJson: String) {

    }


    override fun requestServer(vararg args: Any) {
       mBaseModel.requestToServer(args)
    }

    override fun getModel(): BaseModel {
        return WxNewsModel(this)
    }

    fun requestBanner() {
        mBaseModel.requestToServer()
    }

    fun addFavorite(vararg args: Any) {
        (mBaseModel as WxNewsModel).addFavorite(args)
    }

    fun ServerResponse(bannerBo: List<BannerBo>) {
        mBaseView.bannerrSuccess(bannerBo)
    }

    fun addFavoriteSuccess(it:Any) {
        mBaseView.showDataSuccess(it)
    }


}
