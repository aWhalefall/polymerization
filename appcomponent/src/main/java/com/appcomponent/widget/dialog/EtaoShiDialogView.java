/*
 * File Name: EtaoShiDialogView.java 
 * History:
 * Created by LiBingbing on 2013-9-6
 */
package com.appcomponent.widget.dialog;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.wx.polymerization.appcomponent.R;


public class EtaoShiDialogView extends RelativeLayout {
    // ==========================================================================
    // Constants
    // ==========================================================================

    // ==========================================================================
    // Fields
    // ==========================================================================
    private Activity mActivity;

    private LinearLayout mVisibleArea;

    private RelativeLayout mGrpTitle;

    private TextView mTxtTitle;

    private View mTitleDivider;
    private View mBottomDivider;

    private View mContent;

    private RelativeLayout mContainer;

    private LinearLayout mGrpButtons;

    private TextView mBtnPositive;

    private TextView mBtnNeutral;

    private TextView mBtnNegative;

    private ImageView mImgCancel;


    private View mTitleDividerHView;
    private View mCancelBottomView;
    //3  4  7 10  33
    private final int ViewID_3 = 3;
    private final int ViewID_4 = 4;
    private final int ViewID_7 = 7;
    private final int ViewID_10 = 10;
    private final int ViewID_33 = 33;
    private final int View_ID_44 = 44;
    private final int View_ID_55 = 55;
    public static int setheight = 0;
    public static int setWidth = 0;

    // ==========================================================================
    // Constructors
    // ==========================================================================
    public EtaoShiDialogView(Activity activity) {
        super(activity);
        mActivity = activity;
        initLayout();
    }

    public EtaoShiDialogView(Activity activity, int w, int h) {
        super(activity);
        setheight = h;
        setWidth = w;
        mActivity = activity;
        initLayout();
    }

    // ==========================================================================
    // Getters
    // ==========================================================================

    public View getContentView() {
        return mContent;
    }

    // ==========================================================================
    // Setters
    // ==========================================================================

    // ==========================================================================
    // Methods
    // ==========================================================================
    private void initLayout() {
        LayoutParams rp;
        LinearLayout.LayoutParams lp;

        mVisibleArea = new LinearLayout(mActivity);
        mVisibleArea.setId(View_ID_55);
        mVisibleArea.setOrientation(LinearLayout.VERTICAL);
        int w = getResources().getDimensionPixelSize(R.dimen.dlg_m_width);
        int h = getResources().getDimensionPixelSize(R.dimen.dlg_m_height);
        rp = new LayoutParams(setWidth == 0 ? w : setWidth, setheight == 0 ? h : setheight);
        rp.addRule(RelativeLayout.CENTER_IN_PARENT);
        addView(mVisibleArea, rp);

        // 分隔线
        mTitleDivider = new View(mActivity);
        mTitleDivider.setId(ViewID_4);
        mTitleDivider.setBackgroundColor(mActivity.getResources().getColor(R.color.order_textview_color_border));
        // mTitleDivider.setBackgroundResource(R.drawable.dlg_divider_tile);
        lp = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, 1);
        // rp.addRule(RelativeLayout.BELOW, mGrpTitle.getId());
//        mVisibleArea.addView(mTitleDivider, lp);

        mContainer = new RelativeLayout(mActivity);
        lp = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.MATCH_PARENT);
        lp.weight = 1;
        mVisibleArea.addView(mContainer, lp);

        // 标题栏
        mGrpTitle = new RelativeLayout(mActivity);
        mGrpTitle.setId(ViewID_3);
        rp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        rp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        rp.topMargin = mActivity.getResources().getDimensionPixelSize(R.dimen.dlg_title_margin_top);
        mContainer.addView(mGrpTitle, rp);
        // 标题文字
        mTxtTitle = new TextView(mActivity);
        mTxtTitle.setTextColor(mActivity.getResources().getColor(R.color.color_999999));
        mTxtTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, mActivity.getResources().getDimensionPixelSize(R.dimen.time_title_textsize));
        mTxtTitle.setText(R.string.dialog_title);
        mTxtTitle.setGravity(Gravity.CENTER_HORIZONTAL);
        mTxtTitle.setLineSpacing(5, 1);
        rp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        rp.addRule(RelativeLayout.CENTER_IN_PARENT);
        mGrpTitle.addView(mTxtTitle, rp);

        // 分隔线
        mBottomDivider = new View(mActivity);
        mBottomDivider.setId(ViewID_7);
        mBottomDivider.setBackgroundColor(mActivity.getResources().getColor(R.color.order_textview_color_border));
        //		 mTitleDivider.setBackgroundResource(R.drawable.dlg_divider_tile);
        lp = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, 1);
        // rp.addRule(RelativeLayout.BELOW, mGrpTitle.getId());
//        mVisibleArea.addView(mBottomDivider, lp);


        // 底部按钮区域
        mGrpButtons = new LinearLayout(mActivity);
        mGrpButtons.setId(ViewID_10);
        mGrpButtons.setOrientation(LinearLayout.HORIZONTAL);
        mGrpButtons.setGravity(Gravity.CENTER);
        int marginLeft = getResources().getDimensionPixelSize(R.dimen.dlg_btn_margin_left);
        int marginRight = getResources().getDimensionPixelSize(R.dimen.dlg_btn_margin_right);
        int marginTop = getResources().getDimensionPixelSize(R.dimen.dlg_btn_margin_top);
        int marginBot = getResources().getDimensionPixelSize(R.dimen.dlg_btn_margin_bottom);
        lp = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        lp.setMargins(marginLeft, marginTop, marginRight, marginBot);
        mVisibleArea.addView(mGrpButtons, lp);

        // 左侧按钮
        int buttonWidth = mActivity.getResources().getDimensionPixelSize(R.dimen.dlg_btn_width);
        int buttonHeight = mActivity.getResources().getDimensionPixelSize(R.dimen.dlg_btn_height);
        mBtnNegative = new TextView(mActivity);
        mBtnNegative.setGravity(Gravity.CENTER);
        mBtnNegative.setTextColor(getResources().getColor(R.color.order_textview_color_normal_right));
        mBtnNegative.setTextSize(TypedValue.COMPLEX_UNIT_PX, mActivity.getResources().getDimensionPixelSize(R.dimen.dlg_button_text_size));

        // LinearLayout.LayoutParams negativeParams = new
        // LinearLayout.LayoutParams(buttonWidth,
        // LinearLayout.LayoutParams.WRAP_CONTENT);
        // mGrpButtons.setLayoutParams(negativeParams);

        // 中间按钮
        mBtnNeutral = new TextView(mActivity);
        mBtnNeutral.setVisibility(View.GONE);
        mBtnNeutral.setGravity(Gravity.CENTER);
        mBtnNeutral.setTextColor(mActivity.getResources().getColor(R.color.order_textview_color_normal_right));
        mBtnNeutral.setTextSize(TypedValue.COMPLEX_UNIT_PX, mActivity.getResources().getDimensionPixelSize(R.dimen.dlg_button_text_size));
        // 分隔线
        mTitleDividerHView = new View(mActivity);
        mTitleDividerHView.setBackgroundColor(mActivity.getResources().getColor(R.color.order_textview_color_border));
        // mTitleDivider.setBackgroundResource(R.drawable.dlg_divider_tile);
        LinearLayout.LayoutParams lpHView = new LinearLayout.LayoutParams(1, LinearLayout.LayoutParams.WRAP_CONTENT);
        // 右侧按钮
        mBtnPositive = new TextView(mActivity);
        mBtnPositive.setGravity(Gravity.CENTER);
        mBtnPositive.setTextColor(mActivity.getResources().getColor(R.color.xiaonuo_dialog_cancletextcolor));
        mBtnPositive.setTextSize(TypedValue.COMPLEX_UNIT_PX, mActivity.getResources().getDimensionPixelSize(R.dimen.dlg_button_text_size));

        // LinearLayout.LayoutParams positiveParams = new
        // LinearLayout.LayoutParams(buttonWidth,
        // LinearLayout.LayoutParams.WRAP_CONTENT);
        // mGrpButtons.setLayoutParams(positiveParams);
        //  显示左右按钮状态
        // if (BuildOption.USE_ICS_FEATURE) {
        // lp = new LinearLayout.LayoutParams(buttonWidth, buttonHeight);
        // lp.weight = 0;
        // mGrpButtons.addView(mBtnNegative, lp);
        // lp = new LinearLayout.LayoutParams(buttonWidth, buttonHeight);
        // lp.weight = 0;
        // lp.leftMargin = padding;
        // mGrpButtons.addView(mBtnNeutral, lp);
        // lp = new LinearLayout.LayoutParams(buttonWidth, buttonHeight);
        // lp.weight = 0;
        // lp.leftMargin = padding;
        // mGrpButtons.addView(mBtnPositive, lp);
        // } else {

        int padding = mActivity.getResources().getDimensionPixelSize(R.dimen.dlg_btn_margin_mid);

        lp = new LinearLayout.LayoutParams(buttonWidth, buttonHeight);
        lp.weight = 1;
        lp.rightMargin = padding;
        mGrpButtons.addView(mBtnNegative, lp);

        lp = new LinearLayout.LayoutParams(buttonWidth, buttonHeight);
        lp.weight = 1;
        mGrpButtons.addView(mBtnPositive, lp);

        buttonWidth = mActivity.getResources().getDimensionPixelSize(R.dimen.dlg_btn_one_width);
        lp = new LinearLayout.LayoutParams(buttonWidth, buttonHeight);
        lp.weight = 1;
        lp.leftMargin = padding;
        mGrpButtons.addView(mBtnNeutral, lp);


        //底部取消按钮 TODO finish
        mCancelBottomView = new View(mActivity);
        mCancelBottomView.setId(View_ID_44);
        mCancelBottomView.setBackgroundResource(0);
        rp = new LayoutParams(getResources().getDimensionPixelSize(R.dimen.dlg_cancel_width), getResources().getDimensionPixelSize(R.dimen.dlg_cancel_height));
        rp.topMargin = mActivity.getResources().getDimensionPixelSize(R.dimen.dlg_cancel_margin_top);
        rp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        rp.addRule(RelativeLayout.BELOW, View_ID_55);
        addView(mCancelBottomView, rp);

        // }

        // // 分隔线
        // View buttonDivider = new View(mActivity);
        // buttonDivider.setId(R.id.dlg_button_bar_divider);
        // buttonDivider.setBackgroundResource(R.drawable.divider_dialog);
        // rp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, 1);
        // rp.addRule(RelativeLayout.ABOVE, mGrpButtons.getId());
        // mVisibleArea.addView(buttonDivider, rp);
    }

    private void resetButtonsWidth() {
        int visibleButtons = 1 + (mBtnNegative.getVisibility() == View.GONE ? 0 : 1) + (mBtnNeutral.getVisibility() == View.GONE ? 0 : 1);

        int buttonWidth;
        if (visibleButtons == 3) {
            buttonWidth = mActivity.getResources().getDimensionPixelSize(R.dimen.dlg_3_button_width);
            mBtnPositive.getLayoutParams().width = buttonWidth;
            mBtnNeutral.getLayoutParams().width = buttonWidth;
            mBtnNegative.getLayoutParams().width = buttonWidth;
        } else if (visibleButtons == 2) {
            buttonWidth = mActivity.getResources().getDimensionPixelSize(R.dimen.dlg_2_button_width);
            mBtnPositive.getLayoutParams().width = buttonWidth;
            mBtnNeutral.getLayoutParams().width = buttonWidth;
            mBtnNegative.getLayoutParams().width = buttonWidth;
        } else {
            buttonWidth = mActivity.getResources().getDimensionPixelSize(R.dimen.dlg_1_button_width);
            mBtnPositive.getLayoutParams().width = buttonWidth;
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mBtnPositive.getLayoutParams();
            lp.leftMargin = 5;
            // mBtnNeutral.getLayoutParams().width = buttonWidth;
            // mBtnNegative.getLayoutParams().width = buttonWidth;
        }
    }

    public void setTitle(int textResId) {
        setTitle(mActivity.getString(textResId));
    }

    public void setTitle(CharSequence text) {
        if (mTxtTitle != null) {
            mTxtTitle.setText(text);
        }
    }

    // public void setTitleBackgroundColor(int color) {
    //
    // mGrpTitle.setBackgroundColor(color);
    // }
    //
    public void setContainerBackground(int color) {
        mContainer.setBackgroundColor(color);
    }

    public void setBuilderSize(int w, int h) {
    }

    public void setVisibleAreaBackgroudColor(int color) {
        mVisibleArea.setBackgroundColor(color);
    }

    public void setVisibleAreaBackgroudResource(int resId) {
        mVisibleArea.setBackgroundResource(resId);
    }

    public void setBottomBackgroundColor(int color) {
        mGrpButtons.setBackgroundColor(color);
    }

    public void setPositiveButtonText(int textResId) {
        setPositiveButtonText(mActivity.getString(textResId));
    }

    public void setPositiveButtonText(CharSequence text) {
        mBtnPositive.setText(text);
    }

    public void setPositiveButtonClickable(boolean clickable) {
        mBtnPositive.setClickable(clickable);
    }

    public void setPositiveButtonEnable(boolean enable) {
        mBtnPositive.setEnabled(enable);
    }

    public void setPositiveButtonListener(OnClickListener l) {
        mBtnPositive.setOnClickListener(l);
    }

    public void setNeutralButtonVisible(boolean visible) {
        mBtnNeutral.setVisibility(visible ? View.VISIBLE : View.GONE);
        resetButtonsWidth();
    }

    public void setCancelBottomViewListener(OnClickListener l) {
        mCancelBottomView.setOnClickListener(l);
    }

    public void setNeutralButtonText(int textResId) {
        setNeutralButtonText(mActivity.getString(textResId));
    }

    public void setNeutralButtonText(CharSequence text) {
        mBtnNeutral.setText(text);
    }

    public void setNeutralButtonListener(OnClickListener l) {
        mBtnNeutral.setOnClickListener(l);
    }

    public void setNegativeButtonVisible(boolean visible) {
        mBtnNegative.setVisibility(visible ? View.VISIBLE : View.GONE);
        resetButtonsWidth();
    }

    public void setNegativeButtonText(int textResId) {
        setNegativeButtonText(mActivity.getString(textResId));
    }

    public void setNegativeButtonTextColor(int textColor) {
        mBtnNegative.setTextColor(textColor);
    }

    public void setNegativeButtonText(CharSequence text) {
        mBtnNegative.setText(text);
        mBtnNegative.setVisibility(View.VISIBLE);
    }

    public void setNegativeButtonListener(OnClickListener l) {
        mBtnNegative.setOnClickListener(l);
    }

    public void setTitleVisible(boolean b) {
        int visibility = b ? View.VISIBLE : View.GONE;
        mGrpTitle.setVisibility(visibility);
        mTitleDivider.setVisibility(visibility);
    }

    public void setButtonsVisible(boolean b) {
        int visibility = b ? View.VISIBLE : View.GONE;
        mGrpButtons.setVisibility(visibility);
    }


    public void setCancelBottomViewVisible(boolean isvisiable) {
        int visiable = isvisiable ? View.VISIBLE : View.GONE;
        this.mCancelBottomView.setVisibility(visiable);
    }

    public void setButtonBarBackgroundResource(int resId) {
        mGrpButtons.setBackgroundResource(resId);
    }

    public void setButtonBarBackgroundDrawable(Drawable drawable) {
        mGrpButtons.setBackgroundDrawable(drawable);
    }

    //解决：申请转让下面灰色的线也是黄色的。
    public void setTitleDividerColor(int color) {
        mTitleDivider.setBackgroundColor(color);
    }

    public void setTitleTextColor(int color) {
        mTxtTitle.setTextColor(color);
    }

    public void setButtonPositiveTextColor(int color) {
        mBtnPositive.setTextColor(color);
    }

    public void setButtonTextColor(int color) {
        mBtnPositive.setTextColor(color);
        mBtnNegative.setTextColor(color);
    }

    public void setButtonResource(int resId) {
        mBtnPositive.setBackgroundResource(resId);
        mBtnNegative.setBackgroundResource(resId);
    }

    public void setBtnPositiveBackgroundResource(int resId) {
        mBtnPositive.setBackgroundResource(resId);
    }

    public void setBtnNegativeBackgroundResource(int resId) {
        mBtnNegative.setBackgroundResource(resId);
    }

    public void setBtnNeutralBackgroundResource(int resId) {
        mBtnNeutral.setBackgroundResource(resId);
    }

    public void setBottomDivideColor(int color) {
        mTitleDividerHView.setBackgroundColor(getResources().getColor(color));
    }


    public void setButtonDrawable(int resId) {
        mBtnPositive.setBackgroundDrawable(mActivity.getResources().getDrawable(resId));
        mBtnNegative.setBackgroundDrawable(mActivity.getResources().getDrawable(resId));
    }

    public void setDivider(int resId) {
        mTitleDivider.setBackgroundResource(resId);
    }

    public void setTitleView(View v) {
        if (v == null) {
            return;
        }
        mGrpTitle.removeAllViews();
        mGrpTitle.getLayoutParams().height = LayoutParams.WRAP_CONTENT;
        ViewGroup.LayoutParams lp = v.getLayoutParams();
        if (!(lp instanceof LayoutParams)) {
            v.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        }
        mGrpTitle.addView(v);
    }

    public void setContentView(final View v) {
        if (null != mContent) {
            mContainer.removeView(mContent);
        }

        mContent = v;
        if (mContent == null) {
            return;
        }

        mContent.setId(ViewID_33);
        final LayoutParams rp = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        rp.addRule(RelativeLayout.CENTER_IN_PARENT);
        rp.addRule(RelativeLayout.BELOW, ViewID_3);
        mContainer.addView(mContent, rp);
    }

    public void setTextContent(int msgResId) {
        setTextContent(msgResId, false);
    }

    public void setTextContent(int msgResId, boolean scrollable) {
        setTextContent(mActivity.getString(msgResId), scrollable);
    }

    public void setTextContent(final CharSequence msg) {
        setTextContent(msg, false);
    }


    public void setTextContent(final CharSequence msg, boolean scrollable) {
        final TextView v = new TextView(mActivity);
        v.setTextColor(mActivity.getResources().getColor(R.color.color_999999));
        v.setTextSize(TypedValue.COMPLEX_UNIT_PX, mActivity.getResources().getDimensionPixelSize(R.dimen.text_16sp));
        v.setGravity(Gravity.CENTER);
        int padding = mActivity.getResources().getDimensionPixelSize(R.dimen.global_item_height_small);
        v.setPadding(padding, padding, padding, padding);
        v.setText(msg);
        setContentView(v);

        if (scrollable) {
            // 文字过长时需要在外部套用一层ScrollView，但是由于添加ScrollView的效率较低，会导致Dialog弹出速度变慢，因此使用
            // postDelayed的方式在一小段时间之后再添加，可保证Dialog的弹出速度
            postDelayed(new Runnable() {

                public void run() {
                    setContentView(null);
                    ScrollView scrollView = new ScrollView(getContext());
                    RelativeLayout container = new RelativeLayout(getContext());
                    container.addView(v, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                    scrollView.addView(container, new ScrollView.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                    setContentView(scrollView);
                }

            }, 300);
        }
    }

    public void setListContent(ListAdapter adapter) {
        ListView v = new ListView(mActivity);
        v.setCacheColorHint(0);
        v.setBackgroundColor(0);
        v.setAdapter(adapter);

        setContentView(v);
    }

    public void setWebContent(String msg) {
        WebView v = new WebView(mActivity);
        // v.loadData(msg, "text/html", "UTF-8");;
        v.loadDataWithBaseURL("", msg, "text/html", "utf-8", "");
        v.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                try {
                    mActivity.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                }
                return true;
            }
        });

        setContentView(v);
    }

    public void setPositiveButtonVisible(boolean visible) {
        mBtnPositive.setVisibility(visible ? View.VISIBLE : View.GONE);
        resetButtonsWidth();
    }


    // ==========================================================================
    // Inner/Nested Classes
    // ==========================================================================
}
