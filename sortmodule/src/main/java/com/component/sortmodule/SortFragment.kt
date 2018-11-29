package com.component.sortmodule

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.appcomponent.base.BaseFragment
import com.appcomponent.router.PathConfig
import com.component.router.delegate.SortFragmentDelegate
import com.component.sortmodule.business.SortPresenter
import com.component.sortmodule.business.SortView
import com.component.sortmodule.model.SortBo
import io.github.armcha.recyclerviewkadapter.kadapter.Kadapter
import io.github.armcha.recyclerviewkadapter.kadapter.setUp
import kotlinx.android.synthetic.main.fragment_sort.*
import kotlinx.android.synthetic.main.item_sortlist.view.*

/**
 * 分类列表的
 */
@Route(path = PathConfig.VIDEO_FRAGMENT)
class SortFragment : BaseFragment(), SortFragmentDelegate, SortView {


    private lateinit var sortPresenter: SortPresenter

    private lateinit var kadapter: Kadapter<SortBo>


    var newList: MutableList<SortBo> = ArrayList()
    override val fragment: BaseFragment
        get() = this

    override fun initContentView(): Int {
        return R.layout.fragment_sort
    }

    override fun setContainerId(id: Int) {

    }

    override fun init(context: Context) {

    }

    override fun initParameter() {
        super.initParameter()
        sortPresenter = SortPresenter(this)
    }

    override fun initValue() {
        super.initValue()
        sortPresenter.requestServer()
    }

    override fun showLoading(isShow: Boolean) {
    }

    override fun showDataSuccess(msg: String) {
    }

    override fun showDataFailure(msg: String) {

    }

    override fun showDataSuccess(obj: Any) {
        if (obj is ArrayList<*>) {
            newList.addAll(obj as ArrayList<SortBo>)
            kadapter = recycle_view.setUp(newList, R.layout.item_sortlist, {
                //header 调整
                txt_top.text = it.name
//                txt_content.text
                var sb = StringBuilder()
                it.children?.forEach {
                    sb.append(it.name + "  ")
                }
                txt_content.text = sb.toString()
            }, {
                ARouter.getInstance().build(PathConfig.ARTICLE_ACTIVITY).withSerializable("bo",this).navigation()
            })
        }
    }
}
