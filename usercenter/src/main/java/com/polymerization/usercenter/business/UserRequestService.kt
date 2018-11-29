package com.polymerization.usercenter.business

import com.polymerization.core.retrofit.respond.Response
import io.reactivex.Observable
import retrofit2.http.GET


/**
 * Author: yangweichao
 * Date:   2018/11/17 11:52 AM
 * Description: 分模块进行区分各自的
 */


interface UserRequestService {

    //退出
    @GET("user/logout/json")
    fun exit(): Observable<Response<Any>>

}