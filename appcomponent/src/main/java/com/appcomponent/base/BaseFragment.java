package com.appcomponent.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appcomponent.base.action.BaseActionTemplate;
import com.appcomponent.base.action.BaseTemplate;
import com.appcomponent.base.action.ProxyActionTemplate;
import com.appcomponent.widget.CompositeDisposableManager;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.umeng.analytics.MobclickAgent;

public abstract class BaseFragment extends RxFragment implements BaseTemplate {

    protected Context context;

    BaseActionTemplate deleteAction;
    protected View rootView;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.context = activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initParameter();
        rootView = inflater.inflate(initContentView(), container, false);
        return rootView;
    }

    /**
     * rootView 初始化
     *
     * @return
     */
    protected abstract int initContentView();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initListener();
        initValue();
    }

    protected <SubView extends View> SubView findView(int resId) {
        return (SubView) rootView.findViewById(resId);
    }


    @Override
    public void onStart() {
        super.onStart();
        deleteAction = new ProxyActionTemplate();
    }

    @Override
    public void initParameter() {

    }

    @Override
    public void initLayout() {

    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(context);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(context);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        bindUntilEvent(FragmentEvent.DESTROY);
        //todo 取消解绑原理
        CompositeDisposableManager.INSTANCE.removeAll();
    }
}
