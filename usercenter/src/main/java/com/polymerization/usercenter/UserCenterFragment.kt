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
import com.lib.dialogext.extoast.Ts
import com.polymerization.core.utils.sptool.SpManager
import com.polymerization.usercenter.business.UserCenterPresenter
import com.polymerization.usercenter.business.UserCenterView
import com.wc.polymerization.usercenter.R
import kotlinx.android.synthetic.main.activity_user_center.*

@Route(path = "/mine/usercenter")
class UserCenterFragment : BaseFragment(), MineFragmentDelegate, View.OnClickListener, UserCenterView {


    internal var TAG = UserCenterFragment::class.java.simpleName

    private lateinit var userPresenter: UserCenterPresenter

    override fun showLoading(isShow: Boolean) {

    }

    override fun showDataSuccess(msg: String) {

    }

    override fun showDataFailure(msg: String) {

    }

    override fun showDataSuccess(obj: Any) {
        AccountManager.getInstance().setAccount(context, null)
        //清除cookie
        if (SpManager.getSp("spCookie") != null)
            SpManager.getSp("spCookie").clear()
        //刷新界面
        initValue()
    }


    override val fragment: BaseFragment
        get() = this

    var userInfo: UserInfo? = null
    override fun setContainerId(id: Int) {

    }


    override fun initContentView(): Int {
        return R.layout.activity_user_center
    }

    override fun initParameter() {
        userPresenter = UserCenterPresenter(this)
    }

    override fun init(context: Context) {

    }

    override fun initListener() {
        txt_username.setOnClickListener(this)
        txt_hot.setOnClickListener(this)
        btn_exit.setOnClickListener(this)
        tv_budget_set.setOnClickListener(this)
    }



    override fun initValue() {
        userInfo = AccountManager.mAccount
        if (userInfo != null) {
            txt_username.text = userInfo!!.username + "\n" + userInfo!!.email
            txt_username.isClickable = false
            btn_exit.visibility = View.VISIBLE
        } else {
            txt_username.text = "登录/注册"
            txt_username.isClickable = true
            btn_exit.visibility = View.GONE
        }
    }

    override fun onResume() {
        super.onResume()
        initValue()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.txt_username -> {
                ArouterHelper.startActivity(PathConfig.LOGIN_ACTIVITY)
            }
            R.id.tv_budget_set -> {
                ArouterHelper.startActivity(PathConfig.FAVORITELIST_ACTIVITY)
            }
            R.id.txt_hot -> {
                ArouterHelper.startActivity(PathConfig.TAG_HOTKEY_ACTIVITY)
            }
            R.id.btn_exit -> {
                ExtDialogUtils.exit(context as Activity) { dialog, _ ->
                    dialog.dismiss()
                    Ts.show("确定")
                    userPresenter.requestServer("")
                }
            }
        }

    }
}
