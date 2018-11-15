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

    public static final String MINE_REBATE_ACTIVITY = GROUP_REBATE + "mine_rebate_activity";

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


    /**
     * 进入网络
     */
    public static final String GROUP_COM = "/com/";

    public static final String WEBVIEW_ACTIVITY = GROUP_COM + "webview_activity";

}
