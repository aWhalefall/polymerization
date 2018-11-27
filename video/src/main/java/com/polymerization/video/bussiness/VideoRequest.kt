package com.polymerization.video.bussiness

import com.polymerization.core.retrofit.respond.Response
import com.polymerization.video.model.ProjectBo
import io.reactivex.Observable

import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Author: yangweichao
 * Date:   2018/11/17 11:52 AM
 * Description: 分模块进行区分各自的
 */


interface VideoRequest {

    //获取项目列表
    @GET("article/listproject/{page}/json")
    fun getProjectLists(
            @Path("page") v2: String): Observable<Response<ProjectBo>>

}