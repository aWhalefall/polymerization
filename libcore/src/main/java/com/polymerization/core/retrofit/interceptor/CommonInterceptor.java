package com.polymerization.core.retrofit.interceptor;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 封装公共参数（Key和密码）
 * <p>
 */
public class CommonInterceptor implements Interceptor {
    private final String mApiKey;
    private final String mApiSecret;

    public CommonInterceptor(String apiKey, String apiSecret) {
        mApiKey = apiKey;
        mApiSecret = apiSecret;
    }

    @Override public Response intercept(Interceptor.Chain chain) throws IOException {
        Request oldRequest = chain.request();

        // 添加新的参数
        HttpUrl.Builder authorizedUrlBuilder = oldRequest.url()
                .newBuilder()
                .scheme(oldRequest.url().scheme())
                .setQueryParameter("showapi_appid",mApiKey)
                .setQueryParameter("showapi_sign",mApiSecret)
                .host(oldRequest.url().host());


        // 新的请求
        Request newRequest = oldRequest.newBuilder()
                .method(oldRequest.method(), oldRequest.body())
                .url(authorizedUrlBuilder.build())
                .build();

        return chain.proceed(newRequest);
    }
}