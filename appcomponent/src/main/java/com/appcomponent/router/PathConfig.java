package com.appcomponent.router;

/**
 * /**
 * Author: yangweichao
 * Date:   2018/11/14 5:40 PM
 * Description: 路由路径配置， 路径需要注意的是至少需要有两级，/xx/xx
 */

public class PathConfig {


    /**
     * 新闻
     */
    private static final String GROUP_REBATE = "/news/";

    public static final String NEWS_FRAGMENT_REBATE = GROUP_REBATE + "news_fragment_delegate";




    /**
     * 分类
     */
    private static final String GROUP_VIDEO = "/video/";

    public static final String VIDEO_FRAGMENT = GROUP_VIDEO + "video_fragment_delegate";
    public static final String ARTICLE_ACTIVITY = GROUP_VIDEO + "article_activity";


    /**
     * activity 路径
     */

    /**
     * 登录
     */
    public static final String GROUP_LOGIN = "/login/";

    public static final String LOGIN_ACTIVITY = GROUP_LOGIN + "login_activity";

    /**
     * 进入主页面
     */
    public static final String GROUP_MAIN = "/main/";

    public static final String MAIN_ACTIVITY = GROUP_MAIN+ "main_activity";
    public static final String VIDEO_ACTIVITY = GROUP_MAIN+ "video_activity";


    /**
     * 进入网络
     */
    public static final String GROUP_COM = "/com/";

    public static final String WEBVIEW_ACTIVITY = GROUP_COM + "webview_activity";
    /**
     * 用户中心
     */
    public static final String GROUP_CENTER = "/usencenter/";

    public static final String FAVORITELIST_ACTIVITY = GROUP_CENTER + "favoritelist_activity";

}
