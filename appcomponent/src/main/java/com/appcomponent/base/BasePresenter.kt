package com.appcomponent.base

/**
 * Author: yangweichao
 * Date:   2018/11/14 3:07 PM
 * Description:
 * 1.获取 v,m 引用
 * 2.控制M 的网络请求与取消网络
 * 3.将M层返回数据委托给View 处理
 * 4.网络请求过程中 loading，动画控制
 *
 *
 */


interface BasePresenter{

    /**
     * Author: yangweichao
     * Date:   2018/11/14 3:36 PM
     * Description: view 层调用
     */


    fun requestServer(vararg param: Any)

    /**
     * Author: yangweichao
     * Date:   2018/11/14 3:36 PM
     * Description: model 层调用
     */

    fun requestSuccess(responseJson: String)

    /**
     * Author: yangweichao
     * Date:   2018/11/14 3:36 PM
     * Description: model 层调用
     */


    fun requestError(code: Int, errorMsg: String)

    /**
     * Author: yangweichao
     * Date:   2018/11/14 3:32 PM
     * Description: view 用
     */

    fun cancelRequest()

    /**
     * Author: yangweichao
     * Date:   2018/11/14 3:35 PM
     * Description: 拿到model对象
     */

    fun getModel(): BaseModel



}