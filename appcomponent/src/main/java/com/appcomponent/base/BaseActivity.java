package com.appcomponent.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.appcomponent.base.action.BaseActionTemplate;
import com.appcomponent.base.action.BaseTemplate;
import com.appcomponent.base.action.ProxyActionTemplate;

import org.jetbrains.annotations.NotNull;

public abstract class BaseActivity extends AppCompatActivity implements BaseTemplate {

    protected BaseActionTemplate deleteAction;
    protected Activity context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initParameter();
        initLayout();
        initView();
        initListener();
        initValue();
    }

    @Override
    protected void onStart() {
        super.onStart();
        deleteAction = new ProxyActionTemplate();

    }

    @Override
    public void initParameter() {
        context = this;
    }

    @Override
    public void initLayout() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initValue() {

    }

    protected <SubView extends View> SubView findView(int resId) {
        return (SubView) findViewById(resId);
    }


    protected void clickLimit(@NotNull View view) {
        deleteAction.clickLimit(view, context);
    }


    protected void clickLimitMillisecond(@NotNull View view, long millisecond) {
        deleteAction.clickLimitMillisecond(context, view, millisecond);
    }
}
