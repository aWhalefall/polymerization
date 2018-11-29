package com.component.sortmodule

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.appcomponent.base.BaseFragment
import com.appcomponent.base.BaseView
import com.appcomponent.constant.ConstanPool
import com.appcomponent.router.PathConfig
import com.component.sortmodule.business.DetailPresenter
import com.component.sortmodule.model.ArticleResponseBo
import com.component.sortmodule.model.ChildrenBo
import com.component.sortmodule.model.DatasBo
import io.github.armcha.recyclerviewkadapter.kadapter.Kadapter
import io.github.armcha.recyclerviewkadapter.kadapter.setUp
import kotlinx.android.synthetic.main.fragment_submit_view.*
import kotlinx.android.synthetic.main.item_article.view.*

/**
 * 列表子项目
 */
class DetailFragment : BaseFragment(), BaseView {

    private lateinit var childBo: ChildrenBo
    private lateinit var kadapter: Kadapter<DatasBo>


    var newList: MutableList<DatasBo> = ArrayList()


    companion object {
        fun getinstance(argue: Bundle?): BaseFragment {
            var detailFragment = DetailFragment()
            detailFragment.arguments = argue
            return detailFragment
        }

    }

    private lateinit var detailPresenter: DetailPresenter


    override fun initParameter() {

        super.initParameter()
        detailPresenter = DetailPresenter(this)
        var bundle = arguments
        if (null != bundle) {
            childBo = arguments?.getSerializable("childBo") as ChildrenBo
        }
    }

    override fun initContentView(): Int {
        return R.layout.fragment_submit_view
    }

    override fun initValue() {
        super.initValue()
        detailPresenter.requestServer(0, childBo.id)
    }

    override fun showLoading(isShow: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showDataSuccess(msg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showDataFailure(msg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showDataSuccess(obj: Any) {
        if (obj is ArticleResponseBo) {
            newList = obj.datas
            kadapter = recycle_view.setUp(newList, R.layout.item_article, {
                //header 调整
                if (this != kadapter.mHeaderView) {
                    txt_date.text = "时间: ${it.niceDate}"
                    txt_tag.text = "分类: ${it.superChapterName}"
                    txt_summary.text = it.title
                    txt_author.text = "作者: ${it.author}"
                    txt_favorite.visibility = if (it.collect) View.GONE else View.VISIBLE
                    var bo = it
//                    txt_favorite.setOnClickListener {
//                        newPresenter.addFavorite(bo.id)
//                    }
                }
            }, {
                ARouter.getInstance().build(PathConfig.WEBVIEW_ACTIVITY).withString(ConstanPool.WEB_URL, this.link)
                        .navigation()
            })
        }
    }

}