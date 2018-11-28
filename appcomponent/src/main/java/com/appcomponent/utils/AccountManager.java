package com.appcomponent.utils;

import android.content.Context;
import android.text.TextUtils;

import com.appcomponent.model.UserInfo;

/**
 * Author: yangweichao
 * Date:   2018/11/27 4:03 PM
 * Description: 账号管理
 */


public class AccountManager {

    private static final String FILE_NAME = "account.bin";

    private static AccountManager sInstance = null;

    public static UserInfo mAccount;

    public static AccountManager getInstance() {
        if (sInstance == null) {
            sInstance = new AccountManager();
        }
        return sInstance;
    }

    private AccountManager() {
    }

    public UserInfo getAccount(Context context) {
        if (mAccount == null) {
            String data = FileUtils.readInternalFile(context, FILE_NAME);
            if (!TextUtils.isEmpty(data)) {
                mAccount = GsonHelper.getClassFromJsonString(data, UserInfo.class);
            }
        }
        return mAccount;
    }

    public void setAccount(Context context, UserInfo account) {
        mAccount = account;
        FileUtils.writeInternalFile(context, FILE_NAME, GsonHelper.getGsonInstance().toJson(account));
    }

    public boolean isLogin() {
        if (mAccount == null)
            return false;
        return !TextUtils.isEmpty(mAccount.getToken());
    }

    //public String generateCookie() {
    //UserInfo account = getAccount(CoreBase.getInstance());
    // String cookie = "loginname=" + account.data.nickname + ";" + "token=" + account.token;
    // return cookie;
    //}
}
