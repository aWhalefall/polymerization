package com.appcomponent.utils;


import com.safframework.log.L;

/**
 * Created by weichyang on 2017/6/21.
 */

public class UrlConstans {


    public static final byte URL_Type = 1;
    public static final String SALT = "salt";
    public static String BASE_URL = "";

    public static void initURL(boolean debug) {
        int type = URL_Type;
        String typeStr = "";
        if (!debug) {
            type = -1;
        } else {
            //SpTool spTool = SpManager.getCommonSp(AppConfig.config.SP_Config);
            //type = spTool.getInt("URL_Type", URL_Type_Default);
        }
        switch (-1) {
            case -1:
                typeStr = "正式环境";
                BASE_URL = "https://account.ftoutiao.cn/";
                break;
            case 0:
                typeStr = "预发布环境";
                BASE_URL = "https://account.ftoutiao.cn/";
                break;
            case 1:
                typeStr = "测试";
                BASE_URL = "https://account.ftoutiao.cn/";
                break;

        }
        L.e("当前url环境:" + typeStr);
    }


    public static final String SCHEDULE_REFRESH_ACTION = "action.scheduleback.brocast";
    public static final int BACKGROUND = 1; //后台
    public static final int FORWARD_GROUND = 2;//前台
    public static final String REFRESH_TYPE = "type";


    public static final String REQUEST_SALT = "app/login/initSalt"; //密码盐
    public static final String REQUEST_LOGIN = "app/login/onLogin";
    public static final String REQUEST_AUTHLOGIN = "app/login/authLogin";
    public static final String REQUEST_VERIFYSMS = "app/login/verifySms";
    public static final String REQUEST_ONSIGN = "app/login/onSign";
    public static final String REQUEST_LOGINOUT = "app/login/logout";//exit

    //    public static final String REQUEST_ONSIGN = "app/login/onSign";
    //账本相关
    public static final String REQUEST_ADDBOOKCATEGORY = "app/account/addBookCategory";

    public static final String REQUEST_BOOKCATEGORY = "app/account/bookCategory"; //记账类别
    public static final String REQUEST_UPDATEBOOKCATEGORY = "app/account/updateBookCategory";
    public static final String REQUEST_SORTBOOKCATEGORY = "app/account/sortBookCategory"; //账本定制
    public static final String REQUEST_DELETECATEGORY = "app/account/deleteItem"; //删除

    public static final String REQUEST_ADDBOOK = "app/account/addBook";
    public static final String REQUEST_UPDATEBOOK = "app/account/updateBook";
    public static final String REQUEST_DELETEBOOK = "app/account/deleteBook";
    public static final String REQUEST_ADDITEM = "app/account/addItem";
    public static final String REQUEST_UPDATA_ITEM = "app/account/updateItem";

    //首页
    public static final String REQUEST_ITEMLIST = "app/account/itemList"; //账本列表


    //用户相关

    public static final String REQUEST_USERINFO = "app/user/userInfo"; //用户信息接口
    public static final String REQUEST_MNICKNAME = "app/user/mNickname";//修改昵称
    public static final String REQUEST_MPHOTO = "app/user/mPhoto";//修改头像
    public static final String REQUEST_UPDATEPWD = "app/user/updatePwd"; //修改密码


    /**
     * 重置密码
     */
    public static final String REQUEST_RESETPWD = "app/login/resetPwd";

    public static final String REQUEST_ONUPLOAD = "pic/onUpload"; //上传图片


    public static final String REQUEST_UPGRADE = "/app/upgrade"; //检查更新
    public static final String REQUEST_CONFIG = "app/config"; //检查配置


    public static final String REQUEST_MEMBERS = "app/account/members"; //账本成员
    public static final String REQUEST_SHAREBOOK = "app/account/shareBook"; //分享账本

    public static final String REQUEST_PERMISSION = "app/account/permission"; //修改权限

    public static final String REQUEST_QUITBOOK = "app/account/quitBook"; //退出　 非账本管理员


    //version 1
    public static final String REQUEST_BINDMOBILE = "app/user/bindMobile"; //绑定手机号码
    public static final String REQUEST_BINDOPENUSER = "app/user/bindOpenUser"; //绑定第三方平台接口
    public static final String REQUEST_ONSMSLOGIN = "app/login/onSmsLogin"; //手机验证码登录接口
    public static final String REQUEST_EXPORTMAIL = "app/account/exportMail"; //邮件导出条目列表接口


    //返利列表
    public static final String REQUEST_REBATE_EXPLORE = "app/explore/explore"; //返利列表
    public static final String REQUEST_REBATE_FINANCE = "app/explore/finance"; //理财详情
    public static final String REQUEST_REBATE_LOAN = "app/explore/loan"; //有钱花

    public static final String REQUEST_REBATE_LOANLIST = "app/explore/loanlist"; //贷款列表
    public static final String REQUEST_REBATE_CARDLIST = "app/explore/cardlist"; //信用卡列表
    public static final String REQUEST_REBATE_FINANCELIST = "app/explore/financelist"; //理财列表
    public static final String REQUEST_REBATE_REGISTER_LIST = "app/rebate/registerList"; //理财列表

    public static final String REQUEST_REBATE_REGISTER_PHONE = "app/rebate/register"; //登记手机号

    public static final String REQUEST_REBATE_PLATFORM_LIST = "app/rebate/platformList"; //平台列表

    public static final String REQUEST_REBATE_CARDPLANLIST = "app/rebate/cardPlanList"; // 信用卡返利列表

    public static final String REQUEST_REBATE_ADDOPENPAYACCOUNT = "app/user/addOpenPayAccount"; //添加支付宝账户
    public static final String REQUEST_REBATE_OPENPAYACCOUNT = "app/user/openPayAccount"; //查询支付宝账户
    public static final String REQUEST_REBATE_DEL_ACCOUNT = "app/user/delOpenPayAccount"; //删除支付宝账户
    public static final String REQUEST_REBATE_WITHDRAW= "app/rebate/withdraw"; //返利提现




    // 打卡

    public static final String REQUEST_REBATE_GETUP = "app/promotion/getup"; //打卡主界面
    public static final String REQUEST_REBATE_GETUP_SIGN = "app/promotion/getup/sign"; //打卡


    //订单及支付

    public static final String REQUEST_REBATE_SUBMITORDER = "app/trade/submitOrder"; //下单

    public static final String REQUEST_REBATE_PREPAY = "app/trade/prepay";//预支付r

    public static final String REQUEST_REBATE_PAYOVE= "app/trade/payOver";//

    public static final String REQUEST_REBATE_PAYRESULT = "app/trade/payResult"; //获取第三方支付结果（客户端支付成功）


    //我的
    public static final String REQUEST_MINE_REBATE= "app/rebate/rebateList"; //我的返利列表

    //广告
    public static final String REQUEST_INNERAD = "app/innerAd"; //内部广告

    public static final String REQUEST_LAUNCHAD = "app/launchad"; //开屏广告

    //用户钱包
    public static final String REQUEST_WALLET_BALANCE = "app/wallet/balance"; //钱包余额

    public static final String REQUEST_BALANCE = "app/rebate/balance"; //钱包余额

    public static final String REQUEST_WALLET_DETAIL = "app/wallet/detail"; //获取钱包记录

    public static final String REQUEST_ADLIST = "app/explore/adList";

    public static final String REQUEST_PAYTOUSER = "app/trade/payToUser"; //微信提现

}
