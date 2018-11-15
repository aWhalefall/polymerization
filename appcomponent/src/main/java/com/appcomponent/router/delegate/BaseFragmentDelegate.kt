package com.component.router.delegate

import android.support.annotation.IdRes
import com.alibaba.android.arouter.facade.template.IProvider
import com.appcomponent.base.BaseFragment


/**
 * Author: yangweichao
 * Date:   2018/11/14 5:30 PM
 * Description:
 */


interface BaseFragmentDelegate : IProvider {

    val fragment: BaseFragment

    fun setContainerId(@IdRes id: Int)

}
