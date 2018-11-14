//package cn.ftoutiao.account.android.activity.webview;
//
//
//
//import com.acmenxd.logger.Logger;
//
//import java.net.CookieStore;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//import okhttp3.Cookie;
//import okhttp3.HttpUrl;
//
///**
// * Created by RoryHe on 2017/8/1.
// * 支持跨域的cookie管理
// * 支持本地持久化缓存cookie，缓存时间1小时
// */
//public class CacheCookieStore implements CookieStore {
//    public static final String COOKIE_PREFS = "okgo_cookie";        //cookie使用prefs保存
//    public static final String COOKIE_NAME_PREFIX = "cookie_";          //cookie持久化的统一前缀
//    public static List<Cookie> memroryCookies;
//    private final PreferenceUtils cookiePrefs;
//
//    public CacheCookieStore() {
//        cookiePrefs = MyApp.cookiePrefs;
//        memroryCookies = Collections.synchronizedList(new ArrayList<Cookie>());
//        memroryCookies = CookieUtils.getSharedCookie(cookiePrefs, memroryCookies);
//    }
//
//    @Override
//    public void saveCookie(HttpUrl url, List<Cookie> cookies) {
//        List<Cookie> oldCookies = memroryCookies;
//        List<Cookie> newCookies = new ArrayList<>();
//        for (Cookie getCookie : cookies) {
//            for (Cookie oldCookie : oldCookies) {
//                if (getCookie.name().equals(oldCookie.name())) {
//                    newCookies.add(oldCookie);
//                }
//            }
//        }
//        oldCookies.removeAll(newCookies);
//        oldCookies.addAll(cookies);
//        Logger.d("------- saveCoolie =" + oldCookies.toString());
//    }
//
//    @Override
//    public void saveCookie(HttpUrl url, Cookie cookie) {
//        List<Cookie> oldCookies = memroryCookies;
//        List<Cookie> newCookies = new ArrayList<>();
//        for (Cookie oldCookie : oldCookies) {
//            if (cookie.name().equals(oldCookie.name())) {
//                newCookies.add(oldCookie);
//            }
//        }
//        oldCookies.removeAll(newCookies);
//        oldCookies.add(cookie);
//
//    }
//
//    @Override
//    public List<Cookie> loadCookie(HttpUrl url) {
//        List<Cookie> cookies = memroryCookies;
//        if (cookies == null) cookies = new ArrayList<>();
//        Logger.d("------- loadCookie =" + cookies.toString());
//        return cookies;
//    }
//
//    @Override
//    public List<Cookie> getAllCookie() {
//        return memroryCookies;
//    }
//
//    @Deprecated
//    @Override
//    public List<Cookie> getCookie(HttpUrl url) {
//        return memroryCookies;
//    }
//
//    @Deprecated
//    @Override
//    public boolean removeCookie(HttpUrl url, Cookie cookie) {
//        return false;
//    }
//
//    @Deprecated
//    @Override
//    public boolean removeCookie(HttpUrl url) {
//        return false;
//    }
//
//    @Override
//    public boolean removeAllCookie() {
//        memroryCookies.clear();
//        return true;
//    }
//}
