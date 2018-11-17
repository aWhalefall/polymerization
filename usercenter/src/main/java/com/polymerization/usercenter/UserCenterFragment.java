package com.polymerization.usercenter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.appcomponent.base.BaseFragment;
import com.component.router.delegate.MineFragmentDelegate;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

@Route(path = "/mine/usercenter")
public class UserCenterFragment extends BaseFragment implements MineFragmentDelegate {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @NotNull
    @Override
    public BaseFragment getFragment() {

        return this;
    }

    @Override
    public void setContainerId(int id) {

    }

    @Override
    public void init(Context context) {

    }
}
