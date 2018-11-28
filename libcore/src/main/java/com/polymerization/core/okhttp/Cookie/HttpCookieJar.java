package com.polymerization.core.okhttp.Cookie;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Author: yangweichao
 * Date:   2018/11/14 11:28 AM
 * Description: 基础
 *
 *  cookie 尽量取缓存，增加一个过期时间。减少读写
 *
 *  问题1： 限制读，限制写，会有读写不及时问题出现； 登录态与登录态之后的接口需要及时读取cookie，如果在时间点外
 *  会造成登录成功，未写或者读出来旧的cookie
 *  问题2
 *   读写进行分离，读写放到线程 ？
 *
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