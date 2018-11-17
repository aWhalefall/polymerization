package com.polymerization.video.bussiness

import com.polymerization.core.retrofit.respond.Response
import com.polymerization.video.model.FlowerBean
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Author: yangweichao
 * Date:   2018/11/17 11:52 AM
 * Description: 分模块进行区分各自的
 */


interface VideoRequest {

    //花瓣福利
    @GET("819-1")
    fun getFlowerFuli(
            @Query("type") v2: String,
            @Query("num") v3: String,
            @Query("page") v4: String
    ):  Call<Any>
}