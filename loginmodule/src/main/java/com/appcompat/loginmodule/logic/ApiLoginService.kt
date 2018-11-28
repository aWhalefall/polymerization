package com.appcompat.loginmodule.logic

import com.appcomponent.constant.UrlConstans
import com.appcomponent.model.UserInfo
import com.polymerization.core.retrofit.respond.Response
import io.reactivex.Observable
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Author: yangweichao
 * Date:   2018/11/23 10:41 AM
 * Description: 登录
 */


interface ApiLoginService {


    /**
     * 登录
     */
    @POST(UrlConstans.REQUEST_LOGIN)
    fun login(@Query("username") v3: String,
              @Query("password") v4: String): Observable<Response<UserInfo>>

}
