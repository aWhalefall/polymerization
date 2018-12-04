package com.polymerization.usercenter

import android.view.View
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.appcomponent.base.SimpleBaseView
import com.appcomponent.base.ToolbarBaseActivity
import com.appcomponent.router.PathConfig
import com.lib.dialogext.extoast.Ts
import com.polymerization.usercenter.business.TagCloudPresenter
import com.polymerization.usercenter.model.HotKeyBo
import com.wc.polymerization.usercenter.R
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import com.zhy.view.flowlayout.TagFlowLayout
import kotlinx.android.synthetic.main.activity_tag_cloud.*


/**
 * Author: yangweichao
 * Date:   2018/12/4 2:48 PM
 * Description: 标签云
 */

@Route(path = PathConfig.TAG_HOTKEY_ACTIVITY)
class TagCloudActivity : ToolbarBaseActivity(), SimpleBaseView, TagFlowLayout.OnTagClickListener {

    private lateinit var tagPresenter: TagCloudPresenter

    override fun initParameter() {
        super.initParameter()
        tagPresenter = TagCloudPresenter(this)
        tagPresenter.requestServer()
    }

    override fun initLayout() {
        super.initLayout()
        setContentView(R.layout.activity_tag_cloud)
    }


    override fun initListener() {
        super.initListener()
        id_flowlayout.setOnTagClickListener(this)
    }

    override fun getActionBarId(): Int {
        return R.id.toolbar
    }

    override fun getActionBarTitleId(): Int {
        return R.id.actionbar_title
    }

    private lateinit var list: List<HotKeyBo>

    override fun showDataSuccess(obj: Any) {
        list = obj as List<HotKeyBo>
        id_flowlayout.adapter = object : TagAdapter<HotKeyBo>(list) {
            override fun getView(parent: FlowLayout?, position: Int, t: HotKeyBo): View {
                val tv = View.inflate(this@TagCloudActivity, R.layout.item_tag, null) as TextView

                tv.text = t.name
                return tv
            }

        }
    }

    override fun showDataFailure(msg: String) {
        Ts.show(msg)
    }

    override fun onTagClick(view: View?, position: Int, parent: FlowLayout?): Boolean {
        // TODO: 2018/12/4  后端没有返回
        return true
    }


}
