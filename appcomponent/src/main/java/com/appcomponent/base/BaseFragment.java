package com.appcomponent.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appcomponent.base.action.BaseActionTemplate;
import com.appcomponent.base.action.BaseTemplate;
import com.appcomponent.base.action.ProxyActionTemplate;
import com.trello.rxlifecycle2.components.support.RxFragment;

public abstract class BaseFragment extends RxFragment implements BaseTemplate {

    protected Context context;

    BaseActionTemplate deleteAction;
    protected View rootView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
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
        initLayout();
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
    public void initView() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initValue() {

    }

}
