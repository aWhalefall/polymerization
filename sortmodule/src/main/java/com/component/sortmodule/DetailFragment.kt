package com.component.sortmodule

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.appcomponent.base.BaseFragment
import com.appcomponent.base.SimpleBaseView
import com.appcomponent.constant.ConstanPool
import com.appcomponent.router.PathConfig
import com.component.sortmodule.business.DetailPresenter
import com.component.sortmodule.model.ArticleResponseBo
import com.component.sortmodule.model.ChildrenBo
import com.component.sortmodule.model.DatasBo
import com.lib.dialogext.extoast.Ts
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import io.github.armcha.recyclerviewkadapter.kadapter.Kadapter
import io.github.armcha.recyclerviewkadapter.kadapter.setUp
import kotlinx.android.synthetic.main.fragment_submit_view.*
import kotlinx.android.synthetic.main.item_article.view.*

/**
 * 列表子项目
 */
class DetailFragment : BaseFragment(), SimpleBaseView {

    private lateinit var childBo: ChildrenBo
    private lateinit var kadapter: Kadapter<DatasBo>
    private var isLoadMore: Boolean = false
    private var currentPage: Int = 0
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


    override fun initListener() {
        super.initListener()
        refreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                isLoadMore = true
                currentPage++
                detailPresenter.requestServer(currentPage, childBo.id)
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                isLoadMore = false
                currentPage = 0
                detailPresenter.requestServer(0, childBo.id)

            }

        })
    }

    override fun initValue() {
        super.initValue()
        refreshLayout.autoRefresh(1000)
    }

    override fun showDataSuccess(obj: Any) {
        if (obj is ArticleResponseBo) {
            if (isLoadMore) {
                newList.addAll(obj.datas)
                kadapter.update(newList)

                if (kadapter.itemCount > 200) {
                    refreshLayout.finishLoadMoreWithNoMoreData()
                } else {
                    refreshLayout.finishLoadMore()
                }
            } else {
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
                        txt_favorite.setOnClickListener {
                            detailPresenter.requestServer(bo.id)
                        }
                    }
                }, {
                    ARouter.getInstance().build(PathConfig.WEBVIEW_ACTIVITY).withString(ConstanPool.WEB_URL, this.link)
                            .navigation()
                })
                refreshLayout.finishRefresh()
                refreshLayout.setNoMoreData(false)
            }
        } else {
            // TODO: 2018/12/4  刷新优化
            refreshLayout.autoRefresh(1000)
            Ts.show("收藏成功")
        }
    }

}