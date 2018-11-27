package com.polymerization.usercenter;

import android.content.Context;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.appcomponent.base.BaseFragment;
import com.appcomponent.router.ArouterHelper;
import com.appcomponent.router.PathConfig;
import com.component.router.delegate.MineFragmentDelegate;
import com.wc.polymerization.usercenter.R;

import org.jetbrains.annotations.NotNull;

@Route(path = "/mine/usercenter")
public class UserCenterFragment extends BaseFragment implements MineFragmentDelegate, View.OnClickListener {


    String TAG = UserCenterFragment.class.getSimpleName();

    @NotNull
    @Override
    public BaseFragment getFragment() {

        return this;
    }

    @Override
    public void setContainerId(int id) {

    }


    @Override
    protected int initContentView() {
        return R.layout.click_main;
    }

    @Override
    public void initParameter() {

    }

    @Override
    public void init(Context context) {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initValue() {
//
//        Observable.subscribe(new Consumer() {
//            @Override
//            public void accept(Object o) throws Exception {
//                L.d(o.toString());
//            }
//        });
//        Observable.subscribe(new Consumer() {
//            @Override
//            public void accept(Object o) throws Exception {
//                L.d("第二个 " + o.toString());
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        ArouterHelper.startActivity(PathConfig.LOGIN_ACTIVITY);
    }
}
