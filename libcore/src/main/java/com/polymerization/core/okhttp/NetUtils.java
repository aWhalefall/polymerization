package com.polymerization.core.okhttp;


import com.polymerization.core.okhttp.Cookie.HttpCookieJar;
import com.polymerization.core.retrofit.interceptor.CommonInterceptor;
import com.polymerization.core.retrofit.request.Request;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Author: yangweichao
 * Date:   2018/11/13 3:21 PM
 * Description:
 * 1.拦截器设置【日志，公共头部，cache】
 * 2.okhttp 基础参数设置
 * 3.配置https证书
 * 4.初始化retrofit2，okhttp
 * 5.添加转换器
 *
 */


public class NetUtils {

    private static NetUtils mInstance;
    private static Retrofit retrofit;
    private static volatile Request request = null;

    // 填上需要访问的服务器地址
    private static String HOST = "http://www.wanandroid.com/";
    private static long NET_CONNECT_WRITER =10;
    private static long NET_CONNECT_TIMEOUT = 10;
    private static long NET_CONNECT_READ = 30;


    //网络读写时间


    public static NetUtils getInstance() {
        if (mInstance == null) {
            synchronized (NetUtils.class) {
                if (mInstance == null) {
                    mInstance = new NetUtils();
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
                .connectTimeout(NET_CONNECT_TIMEOUT,TimeUnit.SECONDS)
                .writeTimeout(NET_CONNECT_WRITER,TimeUnit.SECONDS)
                .readTimeout(NET_CONNECT_READ,TimeUnit.SECONDS)
                .addInterceptor(new CommonInterceptor("79656", "80ec326d18234d18832d2785f02d7df4"))
                .addInterceptor(new HttpLoggingInterceptor())
                .cookieJar(HttpCookieJar.create())
                .build();

        // 初始化Retrofit
        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(HOST)
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
