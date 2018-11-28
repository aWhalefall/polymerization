package com.module.news.bussniess

import com.appcomponent.base.AbstractBasePresenter
import com.appcomponent.base.BaseModel
import com.appcomponent.model.Nullable
import com.module.news.bussniess.model.ArticleBo
import com.module.news.bussniess.model.BannerBo
import com.module.news.bussniess.model.WxNewsModel

class NewPresenter(mBaseView: NewView, clazz: Class<ArticleBo>) :
        AbstractBasePresenter<Any, ArticleBo>(mBaseView, clazz) {


    override fun requestSuccess(responseJson: String) {

    }

    override fun serverResponse(data: ArticleBo) {
        mBaseView.showDataSuccess(data)
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
        (mBaseView as NewView).bannerrSuccess(bannerBo)
    }

    fun addFavoriteSuccess(it:Any) {
        mBaseView.showDataSuccess(it)
    }


}
