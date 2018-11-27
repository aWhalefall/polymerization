package com.module.news

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.appcomponent.base.BaseFragment
import com.appcomponent.constant.ConstanPool
import com.appcomponent.router.PathConfig
import com.bigkoo.convenientbanner.ConvenientBanner
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator
import com.bigkoo.convenientbanner.holder.Holder
import com.bumptech.glide.Glide
import com.component.router.delegate.NewsFragmentDelegate
import com.module.news.bussniess.NewPresenter
import com.module.news.bussniess.NewView
import com.module.news.bussniess.model.ArticleBo
import com.module.news.bussniess.model.BannerBo
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.wc.polymerization.news.R
import io.github.armcha.recyclerviewkadapter.kadapter.Kadapter
import io.github.armcha.recyclerviewkadapter.kadapter.setUp
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.android.synthetic.main.item_article.view.*

/**
 * Author: yangweichao
 * Date:   2018/11/14 5:35 PM
 * Description: 新闻 fragment
 */
@Route(path = PathConfig.NEWS_FRAGMENT_REBATE)
class NewsFragment : BaseFragment(), NewsFragmentDelegate, View.OnClickListener, NewView {

    private lateinit var kadapter: Kadapter<ArticleBo.DatasBo>


    var newList: MutableList<ArticleBo.DatasBo> = ArrayList()

    private lateinit var newPresenter: NewPresenter

    var currentPage: Int = 1 //当前页面
    var isLoadMore: Boolean = false //是否是加载更多
    private var mrefreshLayout: RefreshLayout? = null
    private lateinit var bannerLayout: View

    fun init() {
        newPresenter = NewPresenter(this, ArticleBo::class.java)

    }



    override fun initContentView(): Int {
        return R.layout.fragment_news
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
        obj as ArticleBo
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
                    //header 调整
                    if (this != kadapter.mHeaderView) {
                        txt_date.text = "发布时间: ${it.niceDate}"
                        txt_tag.text = "分类: ${it.superChapterName}"
                        txt_summary.text = it.title
                        txt_author.text = "作者: ${it.author}"
                    }
                }, {
                    ARouter.getInstance().build(PathConfig.WEBVIEW_ACTIVITY).withString(ConstanPool.WEB_URL, this.link)
                            .navigation()
                })

                kadapter.setHeaderView(bannerLayout)
                refreshLayout.finishRefresh()
                refreshLayout.setNoMoreData(false)


            }


    }

    override fun initView() {
        super.initView()
        bannerLayout = View.inflate(context, R.layout.banner_layout, null)
    }


    override fun initValue() {
        super.initValue()
//        recycle.layoutManager = LinearLayoutManager(activity)
//        recycle.isNestedScrollingEnabled = false
        newPresenter.requestBaner()

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
        newPresenter.requestServer("0")
    }



    override fun initListener() {
        refreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                isLoadMore = true
                currentPage++
                newPresenter.requestServer(currentPage)
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                isLoadMore = false
                currentPage = 0
                newPresenter.requestServer(0)

            }

        })
    }

    var CbHolder = object : CBViewHolderCreator {
        override fun createHolder(itemView: View?): Holder<*> {

            return LocalImageHolderView(itemView)
        }

        override fun getLayoutId(): Int {
            return R.layout.banner
        }

    }



    override fun onClick(v: View?) {

    }

    inner class LocalImageHolderView(itemView: View?) : Holder<BannerBo>(itemView) {
        override fun updateUI(data: BannerBo?) {
            var img = itemView.findViewById<ImageView>(R.id.img_banner)
            if (data != null)
                Glide.with(context).load(data.imagePath).into(img)
        }

        override fun initView(itemView: View) {

        }

    }

    override fun bannerrSuccess(obj: Any) {
        if (obj is List<*>) {
            var list = obj as List<Nothing>
            var convenbanner = bannerLayout.findViewById<ConvenientBanner<*>>(R.id.convenientBanner)
            convenbanner.
                    setPages(CbHolder, list).
                    startTurning(3000).
                    setPointViewVisible(true).setPageIndicator(intArrayOf(R.drawable.enable,R.drawable.disable)).
                    setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
            return
        }
    }


}