package com.wc.polymerization.view

//import utils.ResponseTransformer
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.view.View
import com.appcomponent.base.BaseActivity
import com.appcomponent.base.BaseFragment
import com.appcomponent.router.ArouterHelper
import com.appcomponent.router.PathConfig
import com.appcomponent.widget.bottombar.OnTabReselectListener
import com.appcomponent.widget.bottombar.OnTabSelectListener
import com.component.router.delegate.NewsFragmentDelegate
import com.safframework.log.L
import com.wc.polymerization.R
import com.wc.polymerization.base.AppContext
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Author: yangweichao
 * Date:   2018/11/14 4:05 PM
 * Description:  mvp封装格式借鉴 http://zengfanyu.top/2017/10/22/MVP2/
 */

class MainActivity : BaseActivity(), OnTabSelectListener, OnTabReselectListener {


    val TAG_ONE = 0
    val TAG_TWO = 1
    val TAG_THREE = 2
    val TAG_FOUR = 3
    private lateinit var fm: FragmentManager
    internal var newsFragment: BaseFragment? = null
    private var showFg: Fragment? = null
    private var showPage = TAG_ONE

    override fun init() {
        super.init()
        AppContext.Instance.instance
    }

    override fun initListener() {
        super.initListener()
        bottom.setOnTabSelectListener(this)
        bottom.setOnTabReselectListener { this }

    }

    override fun initLayout() {
        super.initLayout()
        fm = getCompafragmentManager()
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
                    newsFragment = ArouterHelper.getServiceByClazz(NewsFragmentDelegate::class.java).fragment
                }
                showFragment(newsFragment!!, TAG_ONE)
            }
        }
    }

    override fun onTabReSelected(tabId: Int) {
        when (tabId) {
            R.id.tab_news -> {
                if (newsFragment == null) {
                    newsFragment = ArouterHelper.getServiceByClazz(NewsFragmentDelegate::class.java).fragment
                }
                showFragment(newsFragment!!, TAG_ONE)
            }
        }
    }

    private fun showFragment(f: BaseFragment, tagPage: Int) {

        val ft = fm.beginTransaction()
        if (!f.isAdded && null == supportFragmentManager.findFragmentByTag("TAG$tagPage")) {
            if (showFg != null) {
                ft.hide(showFg).add(R.id.main_container, f, "TAG$tagPage")
            } else {
                ft.add(R.id.main_container, f, "TAG$tagPage")
            }
        } else {
            if (showFg != null) {
                ft.hide(showFg).show(f)
            } else {
                ft.show(f)
            }
        }
        showFg = f
        showPage = tagPage
        if (!isFinishing) {
            ft.commitAllowingStateLoss()
            supportFragmentManager.executePendingTransactions()
        }

        if (tagPage != TAG_FOUR) intent.putExtra("page", tagPage)

    }

    override fun initValue() {
        super.initValue()
        showPage = intent.getIntExtra("page", TAG_ONE)
        when (showPage) {
            TAG_ONE -> {
                findViewById<View>(R.id.tab_news).performClick()
            }
//            TAG_TWO -> {
//            }
//
//            TAG_THREE -> {
//            }
//
//            TAG_FOUR -> {
//            }
            else -> {
                L.d("没有选择任何")
            }
        }
    }

    private fun switchFg(page: Int) {

    }


    override fun onDestroy() {
        super.onDestroy()
        intent.putExtra("page", showPage)
    }


}


/**
 * 扩展函数的用法
 */
fun FragmentActivity.getCompafragmentManager(): FragmentManager {
    return this.supportFragmentManager
}