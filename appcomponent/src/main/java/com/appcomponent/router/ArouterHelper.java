package com.appcomponent.router;

import android.app.Activity;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Author: yangweichao
 * Date:   2018/7/26 下午2:50
 * Description: Arouter 帮助类
 */


public class ArouterHelper {


    public static <T extends IProvider> T getServiceByClazz(Class<T> clazz) {
        return ARouter.getInstance().navigation(clazz);
    }

    /**
     * 通过path
     *
     * @param path
     * @return
     */

    public IProvider getServiceByPath(String path) {
        return (IProvider) ARouter.getInstance().build(path).navigation();
    }


    /**
     * 开启Actvity  startActivityForResult（）
     *
     * @param path
     * @param activity
     * @param requestCode
     */
    public static void navigationToActivityForResult(String path, Activity activity, int requestCode) {
        ARouter.getInstance().build(path).navigation(activity, requestCode);
    }

    public static void startActivity(String path) {
        ARouter.getInstance().build(path).navigation();
    }
}
