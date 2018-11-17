package com.polymerization.video

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
import com.component.router.delegate.VideFragmentDelegate
import com.module.video.R
import com.polymerization.video.bussiness.VideoPresenter
import com.polymerization.video.bussiness.VideoView
import com.polymerization.video.model.FlowerBean
import com.polymerization.video.model.ItemEntity
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import io.github.armcha.recyclerviewkadapter.kadapter.Kadapter
import io.github.armcha.recyclerviewkadapter.kadapter.setUp
import kotlinx.android.synthetic.main.item_view.view.*
import kotlinx.android.synthetic.main.videmo_fragment.*
import java.util.*
import kotlin.math.nextDown


/**
 * Author: yangweichao
 * Date:   2018/11/17 11:03 AM
 * Description:视频api
 *
 */


@Route(path = "/module/video")
class VideoFragment : BaseFragment(), VideFragmentDelegate, VideoView {


    private var videoPresenter: VideoPresenter = VideoPresenter(this, FlowerBean::class.java)

    private lateinit var newList: MutableList<ItemEntity>


    init {
        val randomInt = Random().nextInt(8) + 33
        videoPresenter.requestServer(randomInt, 10, 1)
    }

    private lateinit var kadapter: Kadapter<ItemEntity>

    private var isLoadMore: Boolean = false
    override val fragment: BaseFragment
        get() = this

    override fun setContainerId(id: Int) {

    }

    override fun init(context: Context) {
        context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.videmo_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListener()
    }


    private var currentPage: Int = 1

    private fun initListener() {

        refreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                isLoadMore = true
                currentPage++
                val randomInt = Random().nextInt(8) + 33
                videoPresenter.requestServer(randomInt, 10, currentPage)
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                isLoadMore = false
                currentPage = 1
                val randomInt = Random().nextInt(8) + 33
                videoPresenter.requestServer(randomInt, 10, currentPage)

            }

        })

    }


    override fun showDataSuccess(obj: Any) {
        with(obj as FlowerBean) {
            if (isLoadMore) {
                newList.addAll(obj.flowers)
                kadapter.update(newList)

                if (kadapter.itemCount > 200) {
                    refreshLayout.finishLoadMoreWithNoMoreData()
                } else {
                    refreshLayout.finishLoadMore()
                }

            } else {
                newList = obj.flowers
                kadapter = recycle.setUp(newList, R.layout.item_view, {

                    txt_title.text = it.title
                    Glide.with(context).load(it.thumb).into(imgPhoto)
                }, {
                    ARouter.getInstance().build(PathConfig.WEBVIEW_ACTIVITY).withString(ConstanPool.WEB_URL, this.url)
                            .navigation()
                })
                refreshLayout.finishRefresh()
                refreshLayout.setNoMoreData(false)


            }
        }

    }

}