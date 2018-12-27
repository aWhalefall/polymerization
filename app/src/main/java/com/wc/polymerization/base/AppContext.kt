package com.wc.polymerization.base

import android.content.Context
import android.support.multidex.MultiDex
import com.alibaba.android.arouter.launcher.ARouter
import com.appcomponent.utils.AccountManager
import com.lib.dialogext.extoast.Ts
import com.polymerization.core.corebase.CoreBase
import com.polymerization.core.okhttp.NetUtils
import com.polymerization.core.utils.sptool.SpManager
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.umeng.commonsdk.UMConfigure


class AppContext : CoreBase(){

    //设置全局刷新的Header，Footer 样式fd

    init {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { _, _ -> ClassicsHeader(applicationContext) }
        SmartRefreshLayout.setDefaultRefreshFooterCreator { _, _ -> ClassicsFooter(applicationContext) }
    }
   //dd
    override fun onCreate() {
        super.onCreate()
       initArouter()
        //init sharepreference
        SpManager.setContext(this)
        //初始化网路
        NetUtils.getInstance().init()
        //初始化Toaste
        Ts.initTs(this)
        //初始化登录
        AccountManager.getInstance().getAccount(this)

       UMConfigure.init(this, "5c24490db465f55be000030d", "baidu", UMConfigure.DEVICE_TYPE_PHONE, null)

   }

    //静态常量
    object  Instance {
        var instance=this
    }


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    private fun initArouter(){

        ARouter.openLog()     // 打印日志
        ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！全风线上版本需要关闭,否则有安险)

        ARouter.init(this) // 尽可能早，推荐在Application中初始化
    }
}
