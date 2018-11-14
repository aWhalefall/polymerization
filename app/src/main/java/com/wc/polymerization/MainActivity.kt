package com.wc.polymerization

import android.view.View
import bean.JavaBean
import com.appcomponent.base.BaseActivity
import com.safframework.log.L
import io.reactivex.functions.Consumer
import okhttp.NetWorkManager
import utils.ResponseTransformer
import utils.RxJavaUtils

/**
 * Author: yangweichao
 * Date:   2018/11/14 4:05 PM
 * Description:  mvp封装格式借鉴 http://zengfanyu.top/2017/10/22/MVP2/
 */


class MainActivity : BaseActivity() {


    override fun init() {
        super.init()
        setContentView(R.layout.activity_main)
    }

    fun apiRequest(view: View){
        NetWorkManager.getRequest()
                .getWeatherByAddress("79656", "80ec326d18234d18832d2785f02d7df4", "10")
                .compose(ResponseTransformer.handleResult())
                .compose(RxJavaUtils.observableToMain())
                .subscribe(Consumer<JavaBean>() {
                    L.d(it.toString())
                }, Consumer<Throwable>() {
                    L.d("errow")
                })
    }
}
