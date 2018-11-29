package com.polymerization.video

import android.content.Context
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.appcomponent.base.BaseFragment
import com.appcomponent.constant.ConstanPool
import com.appcomponent.router.PathConfig
import com.bumptech.glide.Glide
import com.component.router.delegate.VideoFragmentDelegate
import com.module.video.R
import com.polymerization.video.bussiness.VideoPresenter
import com.polymerization.video.bussiness.VideoView
import com.polymerization.video.model.ProjectBo
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import io.github.armcha.recyclerviewkadapter.kadapter.Kadapter
import io.github.armcha.recyclerviewkadapter.kadapter.setUp
import kotlinx.android.synthetic.main.item_view.view.*
import kotlinx.android.synthetic.main.videmo_fragment.*


/**
 * Author: yangweichao
 * Date:   2018/11/17 11:03 AM
 * Description:视频api
 *
 */


@Route(path = "/module/video")
class VideoFragment : BaseFragment(), VideoFragmentDelegate, VideoView {


    private var videoPresenter: VideoPresenter = VideoPresenter(this)
    private lateinit var newList: MutableList<ProjectBo.DatasBo>
    private lateinit var kadapter: Kadapter<ProjectBo.DatasBo>
    private var isLoadMore: Boolean = false
    private var currentPage: Int = 1



    override fun initContentView(): Int {
        return R.layout.videmo_fragment
    }

    init {
        videoPresenter.requestServer("0")
    }

    override val fragment: BaseFragment
        get() = this

    override fun setContainerId(id: Int) {

    }

    override fun init(context: Context) {
        context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListener()
    }

    override fun initListener() {
        refreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                isLoadMore = true
                currentPage++
                videoPresenter.requestServer(currentPage)
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                isLoadMore = false
                currentPage = 0
                videoPresenter.requestServer(currentPage)
            }

        })

    }


    override fun showDataSuccess(obj: Any) {
        with(obj as ProjectBo) {
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
                kadapter = recycle.setUp(newList, R.layout.item_view, {
                    txt_date.text = "发布时间: ${it.niceDate}"
                    txt_tag.text = "分类: ${it.superChapterName}"
                    txt_summary.text = it.title
                    txt_author.text = "作者: ${it.author}"
                    Glide.with(context).load(it.envelopePic).into(img_holder)
                },{
                    ARouter.getInstance().build(PathConfig.WEBVIEW_ACTIVITY).withString(ConstanPool.WEB_URL, this.link)
                            .navigation()
                })
                refreshLayout.finishRefresh()
                refreshLayout.setNoMoreData(false)


            }
        }

    }

}