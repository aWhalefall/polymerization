package com.polymerization.usercenter;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.appcomponent.base.BaseFragment;
import com.component.router.delegate.MineFragmentDelegate;

import org.jetbrains.annotations.NotNull;

@Route(path = "/mine/usercenter")
public class UserCenterFragment extends BaseFragment implements MineFragmentDelegate {



    @NotNull
    @Override
    public BaseFragment getFragment() {

        return this;
    }

    @Override
    public void setContainerId(int id) {

    }


    @Override
    public void initParameter() {

    }

    @Override
    public void init(Context context) {

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

    @Override
    protected int initContentView() {
        return 0;
    }
}
