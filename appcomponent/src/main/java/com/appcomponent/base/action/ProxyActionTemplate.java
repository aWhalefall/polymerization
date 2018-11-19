package com.appcomponent.base.action;

import android.app.Activity;
import android.view.View;

import org.jetbrains.annotations.NotNull;

/**
 * 统一行为定义
 */
public class ProxyActionTemplate implements BaseActionTemplate {

    BaseActionTemplate delegate = new BaseActionTemplateImpl();


    @Override
    public void clickLimit(@NotNull View view, @NotNull Activity context) {
        delegate.clickLimit(view,context);
    }

    @Override
    public void clickLimitMillisecond(@NotNull Activity context, @NotNull View view, long millisecond) {
        delegate.clickLimitMillisecond(context,view, millisecond);
    }
}
