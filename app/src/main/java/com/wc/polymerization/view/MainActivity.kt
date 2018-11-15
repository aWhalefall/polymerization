package com.wc.polymerization.view

//import utils.ResponseTransformer
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.view.View
import com.appcomponent.base.BaseActivity
import com.appcomponent.base.BaseFragment
import com.appcomponent.router.ArouterHelper
import com.appcomponent.router.PathConfig
import com.appcomponent.widget.bottombar.OnTabReselectListener
import com.appcomponent.widget.bottombar.OnTabSelectListener
import com.wc.polymerization.R
import com.wc.polymerization.base.AppContext

/**
 * Author: yangweichao
 * Date:   2018/11/14 4:05 PM
 * Description:  mvp封装格式借鉴 http://zengfanyu.top/2017/10/22/MVP2/
 */

class MainActivity : BaseActivity(), OnTabSelectListener, OnTabReselectListener {


    private lateinit var appcomFragmentManager: FragmentManager
    internal var newsFragment: BaseFragment ?= null


    override fun init() {
        super.init()
        AppContext.Instance.instance
    }


    override fun initLayout() {
        super.initLayout()
        appcomFragmentManager = getCompafragmentManager()
        setContentView(R.layout.activity_main)
    }

    fun apiRequest(view: View){
        ArouterHelper.startActivity(PathConfig.LOGIN_ACTIVITY)
    }


    fun apiReques2t(view: View){
//        NetWorkManager.getRequest()
//                .getWeatherByAddress("79656", "80ec326d18234d18832d2785f02d7df4", "10")
//                .compose(ResponseTransformer.handleResult())
//                .compose(RxJavaUtils.observableToMain())
//                .subscribe(Consumer<JavaBean>() {
//                    L.d(it.toString())
//                }, Consumer<Throwable>() {
//                    L.d("errow")
//                })
    }

    override fun onTabSelected(tabId: Int) {
        when (tabId) {
            R.id.tab_news -> {
                if (newsFragment == null) {
                    //newsFragment  = ArouterHelper.getServiceByClazz(NewsFragmentDelegate::class.java).fragment
                }
            }
        }
    }

    override fun onTabReSelected(tabId: Int) {

    }


}


/**
 * 扩展函数的用法
 */
fun FragmentActivity.getCompafragmentManager(): FragmentManager {
    return this.supportFragmentManager
}