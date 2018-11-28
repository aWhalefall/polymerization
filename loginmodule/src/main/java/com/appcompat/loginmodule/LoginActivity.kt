package com.appcompat.loginmodule

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.appcompat.loginmodule.logic.LoginPresenter
import com.appcompat.loginmodule.logic.LoginView
import com.appcomponent.base.ToolbarBaseActivity
import com.appcomponent.model.UserInfo
import com.appcomponent.router.PathConfig
import com.appcomponent.utils.AccountManager
import com.lib.dialogext.extoast.Ts
import kotlinx.android.synthetic.main.activity_login.*

@Route(path = PathConfig.LOGIN_ACTIVITY)
class LoginActivity : ToolbarBaseActivity(), View.OnClickListener, LoginView {


    private lateinit var loginPresen: LoginPresenter

    override fun showLoading(isShow: Boolean) {
    }

    override fun showDataSuccess(msg: String) {
    }

    override fun showDataFailure(msg: String) {
    }

    override fun showDataSuccess(obj: Any) {
        //存储
        obj as UserInfo
        AccountManager.getInstance().setAccount(this, obj)
        Ts.show("登录成功")
        finish()

    }


    override fun getActionBarId(): Int {
        return R.id.toolbar
    }

    override fun getActionBarTitleId(): Int {
        return R.id.actionbar_title
    }

    override fun initParameter() {
        super.initParameter()
        loginPresen = LoginPresenter(this)
    }

    override fun initLayout() {
        setContentView(R.layout.activity_login)
    }

    override fun initValue() {
        super.initValue()
        setDefaultTitle("登录")
    }

    override fun initListener() {
        super.initListener()
        login.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        loginPresen.requestServer(edit_account.text.toString(), edit_password.text.toString())
    }


}
