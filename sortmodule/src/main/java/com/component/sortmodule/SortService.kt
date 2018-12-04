package com.component.sortmodule

import com.component.sortmodule.model.ArticleResponseBo
import com.component.sortmodule.model.SortBo
import com.polymerization.core.retrofit.respond.Response
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface SortService {
    /**
     * 分类层级
     */
    @GET("/tree/json")
    fun getTreeList(): Observable<Response<List<SortBo>>>


    // 获取Tag文章
    @GET("article/list/{page}/json")
    fun getDetailById(@Path("page") page: String, @Query("cid") cid: String): Observable<Response<ArticleResponseBo>>

    @POST("lg/collect/{id}/json")
    fun addFavorite(@Path("id") v1: String): Observable<Response<Any>>

}
