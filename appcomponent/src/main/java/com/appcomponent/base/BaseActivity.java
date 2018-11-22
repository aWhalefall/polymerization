package com.appcomponent.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.appcomponent.base.action.BaseActionTemplate;
import com.appcomponent.base.action.BaseTemplate;
import com.appcomponent.base.action.ProxyActionTemplate;
import com.appcomponent.utils.StackManager;
import com.safframework.log.L;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public abstract class BaseActivity extends RxAppCompatActivity implements BaseTemplate {

    protected BaseActionTemplate deleteAction;
    protected Activity context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 将此Activity添加到ActivityStackManager中管理
        StackManager.INSTANCE.addActivity(this);
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
    protected void onResume() {
        super.onResume();
        Observable.interval(1, TimeUnit.SECONDS)
                .doOnDispose(() -> L.i("Unsubscribing subscription from onResume()"))
                .compose(bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(num -> L.i("Started in onResume(), running until in onDestroy(): " + num));
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        StackManager.INSTANCE.removeActivity(this);
        //todo 取消解绑原理
    }
}
