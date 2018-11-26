package com.module.news.bussniess

import com.module.news.bussniess.model.ArticleBo
import com.module.news.bussniess.model.BannerBo
import com.polymerization.core.retrofit.respond.Response
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface Articlesevice {


    @GET("article/list/{page}/json")
    fun getArticle(@Path("page") v1: String): Observable<Response<ArticleBo>>


    /**
     * 首页banner
     */
    @GET("banner/json")
    fun getBanner(): Observable<Response<List<BannerBo>>>

}