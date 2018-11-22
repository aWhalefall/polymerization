package com.module.news

import android.content.Context
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.appcomponent.base.BaseFragment
import com.appcomponent.constant.ConstanPool
import com.appcomponent.router.PathConfig
import com.bumptech.glide.Glide
import com.component.router.delegate.NewsFragmentDelegate
import com.module.news.bussniess.NewPresenter
import com.module.news.bussniess.NewView
import com.polymerization.core.bean.JavaBean
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.wc.polymerization.news.R
import io.github.armcha.recyclerviewkadapter.kadapter.Kadapter
import io.github.armcha.recyclerviewkadapter.kadapter.setUp
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.android.synthetic.main.item_news.view.*


/**
 * Author: yangweichao
 * Date:   2018/11/14 5:35 PM
 * Description: 新闻 fragment
 */
@Route(path = PathConfig.NEWS_FRAGMENT_REBATE)
class NewsFragment : BaseFragment(), NewsFragmentDelegate, View.OnClickListener, NewView {


    private lateinit var kadapter: Kadapter<JavaBean.NewslistEntity>


    var newList: MutableList<JavaBean.NewslistEntity> = ArrayList<JavaBean.NewslistEntity>()

    private lateinit var newPresenter: NewPresenter



    override fun initContentView(): Int {
        return R.layout.fragment_news
    }


    var currentPage: Int = 1 //当前页面
    var isLoadMore: Boolean = false //是否是加载更多
    private var mrefreshLayout: RefreshLayout? = null


    fun init() {
        newPresenter = NewPresenter(this, JavaBean::class.java)
        newPresenter.requestServer( "10", currentPage)
    }



    override fun init(context: Context?) {
        context
    }




    override fun showLoading(isShow: Boolean) {
    }

    override fun showDataSuccess(msg: String) {
    }

    override fun showDataFailure(msg: String) {

    }



    override fun showDataSuccess(obj: Any) {
        with(obj as JavaBean) {
            if (isLoadMore) {
                newList.addAll(newslist)
                kadapter.update(newList)

                if (kadapter.itemCount > 200) {
                    refreshLayout.finishLoadMoreWithNoMoreData()
                } else {
                    refreshLayout.finishLoadMore()
                }

            } else {
                newList = newslist
                kadapter = recycle.setUp(newList, R.layout.item_news, {
                    txt_date.text = it.ctime
                    txt_tag.text = it.description
                    txt_summary.text = it.title
                    Glide.with(context).load(it.picUrl).into(img_news)
                }, {
                    ARouter.getInstance().build(PathConfig.WEBVIEW_ACTIVITY).withString(ConstanPool.WEB_URL, this.url)
                            .navigation()
                })
                refreshLayout.finishRefresh()
                refreshLayout.setNoMoreData(false)


            }
        }


    }


    override fun setContainerId(id: Int) {

    }

    override val fragment: BaseFragment
        get() = this


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        refreshLayout.autoRefresh()

        init()
        initListener()


    }


    override fun initListener() {
        refreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                isLoadMore = true
                currentPage++
                newPresenter.requestServer( "15", currentPage)
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                isLoadMore = false
                currentPage = 1
                newPresenter.requestServer( "15", 1)

            }

        })

    }


    override fun onClick(v: View?) {

    }

}