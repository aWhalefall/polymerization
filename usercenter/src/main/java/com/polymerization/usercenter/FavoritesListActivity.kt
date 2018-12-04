package com.polymerization.usercenter

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.appcomponent.base.SimpleBaseView
import com.appcomponent.base.ToolbarBaseActivity
import com.appcomponent.router.PathConfig
import com.polymerization.usercenter.business.FavoritesPresenter
import com.polymerization.usercenter.model.ArticleBo
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.wc.polymerization.usercenter.R
import io.github.armcha.recyclerviewkadapter.kadapter.Kadapter
import io.github.armcha.recyclerviewkadapter.kadapter.setUp
import kotlinx.android.synthetic.main.activity_favorites_list.*
import kotlinx.android.synthetic.main.item_article.view.*

@Route(path = PathConfig.FAVORITELIST_ACTIVITY)
class FavoritesListActivity : ToolbarBaseActivity(), SimpleBaseView {


    private lateinit var favoritePresenter: FavoritesPresenter
    var currentPage: Int = 1 //当前页面
    var isLoadMore: Boolean = false //是否是加载更多

    private lateinit var kadapter: Kadapter<ArticleBo.DatasBo>


    var newList: MutableList<ArticleBo.DatasBo> = ArrayList()

    override fun initParameter() {
        super.initParameter()
        favoritePresenter = FavoritesPresenter(this)

    }

    override fun initLayout() {
        super.initLayout()
        setContentView(R.layout.activity_favorites_list)
    }

    override fun initView() {
        super.initView()
        refreshLayout.autoRefresh()

    }

    override fun initValue() {
        super.initValue()
        setDefaultTitle("收藏列表")

    }

    override fun getActionBarId(): Int {
        return R.id.toolbar
    }

    override fun getActionBarTitleId(): Int {
        return R.id.actionbar_title
    }

    override fun initListener() {
        refreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                isLoadMore = true
                currentPage++
                favoritePresenter.requestServer(currentPage)
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                isLoadMore = false
                currentPage = 0
                favoritePresenter.requestServer(0)

            }

        })

    }


    override fun showDataSuccess(obj: Any) {
        if (obj is ArticleBo) {
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
                kadapter = recycle.setUp(newList, R.layout.item_article, {

                    txt_date.text = "时间: ${it.niceDate}"
                    txt_tag.visibility = View.GONE
                    txt_summary.text = it.title
                    txt_author.text = "作者: ${it.author}"
                    txt_favorite.visibility = if (it.collect) View.GONE else View.VISIBLE
                    var bo = it
                    txt_favorite.setOnClickListener {
                        //newPresenter.addFavorite(bo.id)
                    }
                }, {
                    ARouter.getInstance().build(com.appcomponent.router.PathConfig.WEBVIEW_ACTIVITY).withString(com.appcomponent.constant.ConstanPool.WEB_URL, this.link)
                            .navigation()
                })

                refreshLayout.finishRefresh()
                refreshLayout.setNoMoreData(false)


            }
        } else {
            //其他位置异常不处理
        }


    }

}
