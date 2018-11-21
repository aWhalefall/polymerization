package com.appcomponent.base;

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
import com.safframework.log.L;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

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
//        Observable.just("d").doOnDispose(() -> L.i("Unsubscribing subscription from onResume()"))
//                .compose(bindUntilEvent(FragmentEvent.PAUSE))
//                .subscribe(num -> L.i("Started in onResume(), running until in onDestroy(): " + num));
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
