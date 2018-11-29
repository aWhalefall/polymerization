package com.component.sortmodule

import android.os.Bundle
import android.support.design.widget.TabLayout.MODE_FIXED
import android.support.design.widget.TabLayout.MODE_SCROLLABLE
import com.alibaba.android.arouter.facade.annotation.Route
import com.appcomponent.base.BaseFragment
import com.appcomponent.base.ToolbarBaseActivity
import com.appcomponent.router.PathConfig
import com.component.sortmodule.model.SortBo
import kotlinx.android.synthetic.main.activity_article_detail.*

/**
 * Author: yangweichao
 * Date:   2018/11/29 3:45 PM
 * Description:
 *
 *
 */

@Route(path = PathConfig.ARTICLE_ACTIVITY)
class ArticleDetailActivity : ToolbarBaseActivity() {

    override fun initLayout() {
        super.initLayout()
        setContentView(R.layout.activity_article_detail)
    }

    private lateinit var fragments: BaseFragment  //统一一个Fragment 复制N份

    private lateinit var sortBo: SortBo

    override fun initParameter() {
        super.initParameter()
        fragments = DetailFragment.getinstance(null)
        intent?.run {
            sortBo = getSerializableExtra("bo") as SortBo
        }

    }

    override fun initValue() {
        super.initValue()

        var stringArray = arrayOfNulls<String>(sortBo.children?.size!!)
        tabLayout.tabMode = if (stringArray.size > 3) MODE_SCROLLABLE else MODE_FIXED
        setDefaultTitle(sortBo.name)
        var list = mutableListOf<BaseFragment>()
        sortBo.children!!.forEachIndexed { index, childrenBo ->
            stringArray[index] = childrenBo.name!!
            var bundle = Bundle()
            bundle.putSerializable("childBo", childrenBo)
            list.add(DetailFragment.getinstance(bundle))
        }
        var adapter = ProjectViewPageAdapter(this.supportFragmentManager, stringArray)
        adapter.setFragments(list)
        tabLayout.setupWithViewPager(vp)
        vp.adapter = adapter
    }


    override fun getActionBarId(): Int {
        return R.id.toolbar
    }

    override fun getActionBarTitleId(): Int {
        return R.id.actionbar_title
    }

}
