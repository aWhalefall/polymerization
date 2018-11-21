package com.lib.dialogext;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lib.dialogext.adapter.InnerWaitPayment;
import com.lib.dialogext.adapter.ListAdapter;
import com.lib.dialogext.widget.NyDialog;

import java.util.ArrayList;


public class ExtDialogUtils {

    /**
     * 样式1 模板
     *
     * @param activity
     */
    public static void modelOne(Context activity) {

        NyDialog.Builder builder = new NyDialog.Builder(activity);
        builder.setTitle("测试");
        builder.setTitleColor(activity.getResources().getColor(R.color.colorAccent));//标题
        //setContentView(rootView).
        builder.setTextContent("哈哈哈");//内容
        // builder.setContainerBackground(getResources();
        // builder.getColor(R.color.color_d2d2d2));//按钮背影，默认只有一个按钮
        //builder.setBackgroundColor(getResources().getColor(R.color.color_f8f8f8)); //总容器背影，含叉号码
        builder.setBottomDivideColor(R.color.colorAccent);
        builder.setTitleDividerColor(R.color.colorAccent); //分割线颜色， 需要修改

        builder.setTitleVisible(true);

        //builder.setBtnNeutralBackgroundResource(R.drawable.btn_com_buy_selected);
        // builder.setNeutralButtonText("中间");
        //builder.setNeutralButtonVisible(true);

        // 背景色
        builder.setVisibleAreaBackgroundResource(R.drawable.circle_bg); //互斥
        //builder.setVisibleAreaBackgroundColor(getResources().getColor(R.color.main_color_white));

        builder.setBottomViableAreaBackgroundColor(activity.getResources().getColor(R.color.main_color_white));
        builder.setPositiveBtnText("确定");
        builder.setNegativeButtonText("取消");

        //  builder.setBtnNegativeBackgroundResource(R.drawable.btn_com_buy_selected); //shap
        //  builder.setBtnPositiveBackgroundResource(R.drawable.btn_com_buy_selected);

        builder.setPositiveBtnTextColor(activity.getResources().getColor(R.color.text_666666));
        builder.setNegativeButtonTextColor(activity.getResources().getColor(R.color.text_666666));
        builder.setCancelBottomViewVisible(true);

        NyDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * 样式2 模板 按钮不吸边
     *
     * @param activity
     */
    public static void modelTwo(Context activity) {
        NyDialog.Builder builder = new NyDialog.Builder(activity);
        builder.
                setTitle("测试").setTitleColor(activity.getResources().getColor(R.color.colorAccent)).//标题
                //setContentView(rootView).
                        setTextContent("哈哈哈").//内容
                // setContainerBackground(getResources().getColor(R.color.color_d2d2d2)).//按钮背影，默认只有一个按钮
                //setBackgroundColor(getResources().getColor(R.color.color_f8f8f8)). //总容器背影，含叉号码
                        setTitleDividerColor(R.color.colorAccent); //分割线颜色， 需要修改

        builder.setTitleVisible(true);

        //builder.setBtnNeutralBackgroundResource(R.drawable.btn_com_buy_selected);
        //builder.setNeutralButtonText("中间");
        //builder.setNeutralButtonVisible(true);

        // 背景色
        builder.setVisibleAreaBackgroundResource(R.drawable.circle_bg); //互斥
//        builder.setVisibleAreaBackgroundColor(getResources().getColor(R.color.main_color_white));
        builder.setmBottomDividerVisiable(View.GONE);
        builder.setBottomViableAreaBackgroundColor(activity.getResources().getColor(R.color.main_color_white));
        builder.setPositiveBtnText("确定");
        builder.setNegativeButtonText("取消");
        builder.setBottomViewPadding(0, 0, 0, 10);
        builder.setBtnNegativeBackgroundResource(R.drawable.btn_com_buy_selected);
        builder.setBtnPositiveBackgroundResource(R.drawable.btn_com_buy_selected);

        builder.setPositiveBtnTextColor(activity.getResources().getColor(R.color.main_color_white));
        builder.setNegativeButtonTextColor(activity.getResources().getColor(R.color.main_color_white));
        builder.setCancelBottomViewVisible(true);

        NyDialog dialog = builder.create();
        dialog.show();

    }

    /**
     * 样式2 模板 按钮不吸边
     *
     * @param activity
     */
    public static void modelThree(Context activity, ArrayList<InnerWaitPayment> dealList) {
        NyDialog.Builder builder = new NyDialog.Builder(activity);
        builder.setTitle("测试");
        builder.setTitleColor(activity.getResources().getColor(R.color.colorAccent));//标题
        builder.setBottomDivideColor(R.color.colorAccent);
        builder.setTitleDividerColor(R.color.colorAccent); //分割线颜色， 需要修改
        ListAdapter listAdapter = new ListAdapter(activity, dealList);
        builder.setListContent(listAdapter);
        builder.setTitleVisible(true);
        builder.setBottomViewPadding(0, 10, 0, 10);
        builder.setVisibleAreaBackgroundColor(activity.getResources().getColor(R.color.main_color_white));
        builder.setBottomViableAreaBackgroundColor(activity.getResources().getColor(R.color.main_color_white));
        builder.setPositiveBtnText("确定");
        builder.setNegativeButtonText("取消");
        builder.setBtnNegativeBackgroundResource(R.drawable.btn_com_buy_selected); //shap
        builder.setBtnPositiveBackgroundResource(R.drawable.btn_com_buy_selected);
        builder.setPositiveBtnTextColor(activity.getResources().getColor(R.color.main_color_white));
        builder.setNegativeButtonTextColor(activity.getResources().getColor(R.color.main_color_white));
        builder.setCancelBottomViewVisible(false);
        NyDialog dialog = builder.create();
        dialog.show();
    }


    public static void modelTransportDialog(Activity activity) {
        NyDialog.Builder builder = new NyDialog.Builder(activity, 250, 140, R.style.dialog);
        builder.setTitleVisible(false);
        builder.setBottomViableAreaVisible(false);
        builder.setmBottomDividerVisiable(View.GONE).setTitleDividerColor(activity.getResources().getColor(R.color.main_color_white));
        builder.setCancelBottomViewVisible(false);
//      builder.setTextContent("中间弹窗").setContainerBackground(R.color.main_color_white);
        builder.setTextContent("中间弹窗").setContainerBackgroundResource(R.drawable.transport_circle_bg);
        Dialog dialog = builder.create();
        Window window = dialog.getWindow();

        // 设置显示动画
        //window.setWindowAnimations(R.style.SlipDialogAnimation);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 设置显示位置
        dialog.onWindowAttributesChanged(wl);
        builder.create().show();
    }

    public static void loadingDialog(Activity activity, View view) {
        NyDialog.Builder builder = new NyDialog.Builder(activity, 250, 140);
        builder.setTitleVisible(false);
        builder.setBottomViableAreaVisible(false);
        builder.setmBottomDividerVisiable(View.GONE).setTitleDividerColor(activity.getResources().getColor(R.color.main_color_white));
        builder.setCancelBottomViewVisible(false);
//      builder.setTextContent("中间弹窗").setContainerBackground(R.color.main_color_white);
        builder.setContainerBackgroundResource(R.drawable.circle_bg);
        builder.setContentView(view);
        Dialog dialog = builder.create();

        Window window = dialog.getWindow();

        // 设置显示动画
        //window.setWindowAnimations(R.style.SlipDialogAnimation);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 设置显示位置
        dialog.onWindowAttributesChanged(wl);
        builder.create().show();
    }

    public static Dialog showPhoneDialog(final Activity activity, final String phoneNumber) {

        View view = activity.getLayoutInflater().inflate(R.layout.dialog_callserveshotline, null);
        NyDialog.Builder builder = new NyDialog.Builder(activity);
        TextView hotlinePhonenumber_tv = (TextView) view.findViewById(R.id.hotlinePhonenumber_tv);
        builder.setBottomViableAreaVisible(false).setTitleVisible(false).setCancelBottomViewVisible(false);
        hotlinePhonenumber_tv.setText(phoneNumber);
        Dialog dialog = builder.create();
        dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        // 设置显示动画
        window.setWindowAnimations(R.style.SlipDialogAnimation);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 设置显示位置
        dialog.onWindowAttributesChanged(wl);
        // 设置点击外围解散

        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        return dialog;
    }

}
