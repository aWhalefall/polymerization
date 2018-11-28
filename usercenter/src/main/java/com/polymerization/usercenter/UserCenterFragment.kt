package com.polymerization.usercenter

import android.app.Activity
import android.content.Context
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.appcomponent.base.BaseFragment
import com.appcomponent.model.UserInfo
import com.appcomponent.router.ArouterHelper
import com.appcomponent.router.PathConfig
import com.appcomponent.utils.AccountManager
import com.component.router.delegate.MineFragmentDelegate
import com.lib.dialogext.ExtDialogUtils
import com.wc.polymerization.usercenter.R
import kotlinx.android.synthetic.main.click_main.*

@Route(path = "/mine/usercenter")
class UserCenterFragment : BaseFragment(), MineFragmentDelegate, View.OnClickListener {


    internal var TAG = UserCenterFragment::class.java.simpleName

    override val fragment: BaseFragment
        get() = this

    var userInfo: UserInfo? = null
    override fun setContainerId(id: Int) {

    }


    override fun initContentView(): Int {
        return R.layout.click_main
    }

    override fun initParameter() {

    }

    override fun init(context: Context) {

    }

    override fun initListener() {
        txt_username.setOnClickListener {
            ArouterHelper.startActivity(PathConfig.LOGIN_ACTIVITY)
        }
        btn_exit.setOnClickListener {
            ExtDialogUtils.exit(context as Activity) { dialog, _ ->
                dialog.dismiss()
            }
        }
    }

    override fun initValue() {
        userInfo = AccountManager.mAccount
        if (userInfo != null) {
            txt_username.text = userInfo!!.username + "\n" + userInfo!!.email
            txt_username.isClickable = false
        } else {
            txt_username.text = "登录/注册"
            txt_username.isClickable = true
        }
    }

    override fun onClick(v: View) {
        ArouterHelper.startActivity(PathConfig.LOGIN_ACTIVITY)
    }
}
