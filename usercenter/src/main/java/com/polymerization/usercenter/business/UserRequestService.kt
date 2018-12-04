package com.polymerization.usercenter.business

import com.polymerization.core.retrofit.respond.Response
import com.polymerization.usercenter.model.ArticleBo
import com.polymerization.usercenter.model.HotKeyBo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Author: yangweichao
 * Date:   2018/11/17 11:52 AM
 * Description: 分模块进行区分各自的
 */


interface UserRequestService {

    //退出
    @GET("user/logout/json")
    fun exit(): Observable<Response<Any>>

    //收藏列表
    @GET("/lg/collect/list/{page}/json")
    fun getFavoriteList(@Path("page") v1: String): Observable<Response<ArticleBo>>


    //热词列表
    @GET("hotkey/json")
    fun getHotKey(): Observable<Response<List<HotKeyBo>>>

}