package com.polymerization.core.okhttp.Cookie;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Author: yangweichao
 * Date:   2018/11/14 11:28 AM
 * Description: 基础
 */


public final class HttpCookieJar implements CookieJar {

    private static HttpCookieJar mCookieManager;
    private static PersistentCookieStore cookieStore;

    private HttpCookieJar() {
        cookieStore = new PersistentCookieStore();
    }

    public static HttpCookieJar create() {
        if (mCookieManager == null) {
            mCookieManager = new HttpCookieJar();
        }
        return mCookieManager;
    }

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if (cookies != null && cookies.size() > 0) {
            for (Cookie item : cookies) {
                cookieStore.add(url, item);
            }
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookies = cookieStore.getCookies(url);
        return cookies;
    }
}