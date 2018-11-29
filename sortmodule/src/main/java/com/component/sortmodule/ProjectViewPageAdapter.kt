package com.component.sortmodule

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.appcomponent.base.BaseFragment

class ProjectViewPageAdapter(fragmentManager: FragmentManager, val titleArray: Array<String?>) : FragmentPagerAdapter(fragmentManager) {

    var fragment = mutableListOf<BaseFragment>()

    var TITLE_FRAGMENTS: Array<String?> = this.titleArray

    override fun getItem(p0: Int): Fragment {
        return fragment[p0]
    }

    override fun getCount() = fragment.size

    override fun getPageTitle(position: Int) = titleArray?.get(position)
            ?: TITLE_FRAGMENTS[position]

    fun setFragments(fragments: MutableList<BaseFragment>) {
        fragment = fragments
        notifyDataSetChanged()
    }
}