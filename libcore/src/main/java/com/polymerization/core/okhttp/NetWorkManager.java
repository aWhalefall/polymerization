package com.polymerization.core.okhttp;


import com.polymerization.core.okhttp.Cookie.HttpCookieJar;

import okhttp3.OkHttpClient;

import com.polymerization.core.retrofit.interceptor.CommonInterceptor;
import com.polymerization.core.retrofit.request.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Author: yangweichao
 * Date:   2018/11/13 3:21 PM
 * Description:
 */



public class NetWorkManager {

    private static NetWorkManager mInstance;
    private static Retrofit retrofit;
    private static volatile Request request = null;

    public static NetWorkManager getInstance() {
        if (mInstance == null) {
            synchronized (NetWorkManager.class) {
                if (mInstance == null) {
                    mInstance = new NetWorkManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 初始化必要对象和参数
     */
    public void init() {
        // 初始化okhttp
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new CommonInterceptor("79656", "80ec326d18234d18832d2785f02d7df4"))
                .cookieJar(HttpCookieJar.create())
                .build();

        // 初始化Retrofit
        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(Request.HOST)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    public static Request getRequest() {
        if (request == null) {
            synchronized (Request.class) {
                request = retrofit.create(Request.class);
            }
        }
        return request;
    }

    /**
     *
     * @param requestServerClazz
     * @param <service>
     * @return
     */
    public static  <service>  service creatRequest(Class<service> requestServerClazz){
        return retrofit.create(requestServerClazz);
    }




}
