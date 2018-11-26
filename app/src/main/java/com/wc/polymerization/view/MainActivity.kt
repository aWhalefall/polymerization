package com.wc.polymerization.view


import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.view.View
import com.appcomponent.base.BaseFragment
import com.appcomponent.base.ToolbarBaseActivity
import com.appcomponent.router.ArouterHelper
import com.appcomponent.widget.bottombar.OnTabReselectListener
import com.appcomponent.widget.bottombar.OnTabSelectListener
import com.component.router.delegate.MineFragmentDelegate
import com.component.router.delegate.NewsFragmentDelegate
import com.component.router.delegate.VideFragmentDelegate
import com.safframework.log.L
import com.wc.polymerization.R
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Author: yangweichao
 * Date:   2018/11/14 4:05 PM
 * Description:  mvp封装格式借鉴 http://zengfanyu.top/2017/10/22/MVP2/
 */

class MainActivity : ToolbarBaseActivity(), OnTabSelectListener, OnTabReselectListener {

    val TAG_ONE = 0
    val TAG_TWO = 1
    val TAG_THREE = 2
    val TAG_FOUR = 3
    private lateinit var fm: FragmentManager
    internal var newsFragment: BaseFragment? = null
    var videoFragment: BaseFragment? = null
    var userCenterFragment: BaseFragment? = null
    private var showFg: Fragment? = null
    private var showPage = TAG_ONE


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


    override fun onTabSelected(tabId: Int) {
        when (tabId) {
            R.id.tab_video -> {
                if (userCenterFragment == null) {
                    userCenterFragment = ArouterHelper.getServiceByClazz(MineFragmentDelegate::class.java).fragment
                }
                showFragment(userCenterFragment!!, TAG_TWO)
                setDefaultTitle("微信精选")
            }
            R.id.tab_news -> {
                if (newsFragment == null) {
                    newsFragment = ArouterHelper.getServiceByClazz(NewsFragmentDelegate::class.java).fragment
                }
                showFragment(newsFragment!!, TAG_ONE)
                setDefaultTitle("精选")
            }

            R.id.tab_search -> {
                if (videoFragment == null) {
                    videoFragment = ArouterHelper.getServiceByClazz(VideFragmentDelegate::class.java).fragment
                }
                showFragment(videoFragment!!, TAG_THREE)
                setDefaultTitle("搜索")
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
            TAG_TWO -> {
                findViewById<View>(R.id.tab_video).performClick()
            }

            TAG_THREE -> {
                findViewById<View>(R.id.tab_search).performClick()
            }
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

    override fun getActionBarId(): Int {
        return R.id.toolbar
    }

    override fun getActionBarTitleId(): Int {
        return R.id.actionbar_title
    }

    override fun provideNavigationIcon(): Int {
        return 0
    }


}


/**
 * 扩展函数的用法
 */
fun FragmentActivity.getCompafragmentManager(): FragmentManager {
    return this.supportFragmentManager
}