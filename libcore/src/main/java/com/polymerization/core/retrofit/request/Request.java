package com.polymerization.core.retrofit.request;

import com.polymerization.core.bean.JavaBean;
import io.reactivex.Observable;
import com.polymerization.core.retrofit.respond.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Request {

    // 填上需要访问的服务器地址
     String HOST = "http://route.showapi.com/";

    @GET("181-1")
    Observable<Response<JavaBean>> getWeatherByAddress(
            @Query("num") String v3,
            @Query("page") String v4
            );


}

