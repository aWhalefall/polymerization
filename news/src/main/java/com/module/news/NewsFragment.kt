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
import com.appcomponent.utils.ResponseTransformer
import com.appcomponent.utils.RxJavaUtils
import com.bumptech.glide.Glide
import com.component.router.delegate.NewsFragmentDelegate
import com.polymerization.core.bean.JavaBean
import com.polymerization.core.okhttp.NetWorkManager
import com.safframework.log.L
import com.wc.polymerization.news.R
import io.github.armcha.recyclerviewkadapter.kadapter.setUp
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.android.synthetic.main.item_news.view.*


/**
 * Author: yangweichao
 * Date:   2018/11/14 5:35 PM
 * Description: 新闻 fragment
 */
@Route(path = PathConfig.NEWS_FRAGMENT_REBATE)
class NewsFragment : BaseFragment(), NewsFragmentDelegate, View.OnClickListener {


    lateinit var newList: MutableList<JavaBean.NewslistEntity>

    override fun init(context: Context?) {
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


        NetWorkManager.getRequest()
                .getWeatherByAddress("79656", "80ec326d18234d18832d2785f02d7df4", "10")
                .compose(ResponseTransformer.handleResult())
                .compose(RxJavaUtils.observableToMain())
                .subscribe(Consumer<JavaBean>() {
                    newList = it.newslist
                    recycle.setUp(newList, R.layout.item_news, {
                        txt_date.text = it.ctime
                        txt_tag.text = it.description
                        txt_summary.text = it.title
                        Glide.with(context).load(it.picUrl).into(img_news)
                    }, {
                        ARouter.getInstance().build(PathConfig.WEBVIEW_ACTIVITY).withString(ConstanPool.WEB_URL, this.url)
                                .navigation()
                    })

                }, Consumer<Throwable>() {
                    L.d("errow")
                })

    }


    override fun onClick(v: View?) {

    }

}