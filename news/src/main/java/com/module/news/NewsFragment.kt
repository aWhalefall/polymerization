package com.module.news

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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


    var currentPage: Int = 1 //当前页面
    var isLoadMore: Boolean = false //是否是加载更多
    private var mrefreshLayout: RefreshLayout? = null



    override fun init(context: Context?) {
        context
    }


    var newList: MutableList<JavaBean.NewslistEntity> = ArrayList()

    private lateinit var newPresenter: NewPresenter


    override fun showLoading(isShow: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showDataSuccess(msg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showDataFailure(msg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var kadapter: Kadapter<JavaBean.NewslistEntity>

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


    fun init() {
        newPresenter = NewPresenter(this, JavaBean::class.java)
        newPresenter.requestServer("79656", "80ec326d18234d18832d2785f02d7df4", "10", currentPage)
    }

    override fun setContainerId(id: Int) {

    }

    override val fragment: BaseFragment
        get() = this


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_news,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        topbar.setTopbarTitle("微信精选")
        topbar.setLineVisiable(View.GONE)
        topbar.setTopbarLeftLayoutHide()
        init()
        initListener()


    }


    private fun initListener() {

        refreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                isLoadMore = true
                currentPage++
                newPresenter.requestServer("79656", "80ec326d18234d18832d2785f02d7df4", "15", currentPage)
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                isLoadMore = false
                currentPage = 1
                newPresenter.requestServer("79656", "80ec326d18234d18832d2785f02d7df4", "15", 1)

            }

        })

    }


    override fun onClick(v: View?) {

    }

}