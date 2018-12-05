package com.appcompat.loginmodule

import android.view.View
import com.appcompat.loginmodule.logic.RegisterPresenter
import com.appcomponent.base.SimpleBaseView
import com.appcomponent.base.ToolbarBaseActivity
import com.lib.dialogext.extoast.Ts
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : ToolbarBaseActivity(), View.OnClickListener, SimpleBaseView {


    private lateinit var registerPresenter: RegisterPresenter


    override fun initLayout() {
        super.initLayout()
        setContentView(R.layout.activity_register)
    }

    override fun getActionBarId(): Int {

        return R.id.toolbar
    }

    override fun getActionBarTitleId(): Int {
        return R.id.action_bar
    }

    override fun initListener() {
        super.initListener()
        button.setOnClickListener(this)
    }


    override fun initValue() {
        super.initValue()
        registerPresenter = RegisterPresenter(this)
        setDefaultTitle("注册")

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.button -> {
                var var1 = editText.text.toString()
                var var2 = editText2.text.toString()
                var var3 = editText3.text.toString()
                if (var1.isEmpty() || var2.isEmpty() || var3.isEmpty()) {
                    Ts.show("不能为null")
                    return
                }
                if (!var2.equals(var3)) {
                    Ts.show("两次输入密码不一致")
                }
                registerPresenter.requestServer(var1, var2, var3)

            }
        }
    }

    override fun showDataSuccess(obj: Any) {
        super.showDataSuccess(obj)
        Ts.show("注册成功")
        finish()
    }


}
