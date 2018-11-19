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

public abstract class BaseFragment extends Fragment implements BaseTemplate {

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
        rootView = initContentView();
        return rootView;
    }

    /**
     * rootView 初始化
     *
     * @return
     */
    protected abstract View initContentView();

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

}
