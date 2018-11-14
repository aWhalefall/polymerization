package com.appcomponent.widget;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wx.polymerization.appcomponent.R;


/**
 * Author: yangweichao
 * Date:   2018/11/14 2:38 PM
 * Description: 进行二次改造
 */


public class TopBar extends RelativeLayout {

    /**
     * 组件的位置，左中右TextView
     */
    public final static int LEFT = 0;
    public final static int MID = 1;
    public final static int RIGHT = 2;

    /**
     * 标题设置drawableTop drawableBottom
     */
    public final static int TOP = 3;
    public final static int BOTTOM = 4;

    /**
     * 右边textview
     */
    private TextView rightTextView = null;
    /**
     * 标题TextView
     */
    private TextView topTitleTextView = null;
    private ImageButton rightButton = null;

    private RelativeLayout topbarBg = null;
    private LinearLayout leftLayout = null;
    private LinearLayout rightContentLayout = null;

    private Context context;

    /**
     * 点击监听
     */
    private OnLeftLayoutListener onLeftLayoutListener = null;
    private OnRightLayoutListener onRightLayoutListener = null;
    private OnMidLayoutListener onMidLayoutListener = null;

    /**
     * 左边textView
     */
    private TextView leftTextView;
    /**
     * 底部通栏线
     */
    private View bottomLine;


    public TopBar(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        LayoutInflater.from(context).inflate(R.layout.include_common_topbar, this, true);
        topbarBg = findViewById(R.id.topbar_layout);
        bottomLine = findViewById(R.id.bottom_line);
        leftLayout = findViewById(R.id.topbarLeftLinearLayout);
        leftTextView = findViewById(R.id.back_tv);
        topTitleTextView = findViewById(R.id.text_title);
        rightContentLayout = findViewById(R.id.rightContentLayout);
        rightButton = findViewById(R.id.ic_comright_iv);
        rightTextView = findViewById(R.id.ic_comright_tv);

        leftLayout.setOnClickListener(new ActionClick());
        topTitleTextView.setOnClickListener(new ActionClick());
        rightButton.setOnClickListener(new ActionClick());
        rightTextView.setOnClickListener(new ActionClick());
    }

    /**
     * 修改TopBar 背景 图片
     *
     * @param drawable
     */
    public void setTopbarBackground(int drawable) {
        if (topbarBg != null) {
            topbarBg.setBackgroundResource(drawable);
        }
    }

    /**
     * 底部通栏线 显示/隐藏
     *
     * @param state
     */
    public void setLineVisiable(int state) {
        if (null != bottomLine) {
            bottomLine.setVisibility(state);
        }
    }

    /**
     * 底部通栏线 颜色
     *
     * @param color
     */
    public void setBackgroundLine(int color) {
        if (null != bottomLine) {
            bottomLine.setBackgroundColor(color);
        }
    }

    /**
     * 修改TopBar 背景 颜色
     *
     * @param color
     */
    public void setTopbarBackgroundColor(int color) {
        if (topbarBg != null) {
            topbarBg.setBackgroundColor(color);
        }
    }

    /**
     * 左边布局显示/隐藏
     */
    public void setTopbarLeftLayoutHide() {
        if (leftLayout != null) {
            leftLayout.setVisibility(View.GONE);
        }
    }


    /**
     * 左边ImageView组件设置前景图片
     *
     * @param backImageResId
     */
    public void setLeftIconResource(int backImageResId) {
        if (leftLayout != null) {
            if (backImageResId >= 0) {
                ImageView backImage = findViewById(R.id.ic_comleft_imgbg);
                backImage.setImageResource(backImageResId);
            }
        }
    }

    public void setLeftIconResource(int backImageResId, int padding) {
        if (leftLayout != null) {
            if (backImageResId >= 0) {
                ImageView backImage = findViewById(R.id.ic_comleft_imgbg);
                backImage.setImageResource(backImageResId);
                backImage.setPadding(padding, padding, padding, padding);
            }
        }
    }

    /**
     * 设置左边文字颜色
     *
     * @param color
     */
    public void setLeftTextColor(int color) {
        TextView backTitle = findViewById(R.id.back_tv);
        backTitle.setTextColor(context.getResources().getColor(color));
    }


    public void setTopbarLeftLayout(int backImageResId, int backTitleResId,
                                    int backTitleColorResId) {
        if (leftLayout != null) {
            if (backImageResId >= 0) {
                ImageView backImage = findViewById(R.id.ic_comleft_imgbg);
                backImage.setImageResource(backImageResId);
            }

            TextView backTitle = findViewById(R.id.back_tv);
            if (backTitleResId > 0) {
                backTitle.setText(backTitleResId);
            }
            if (backTitleColorResId > 0) {
                backTitle.setBackgroundResource(backTitleColorResId);
            }
        }
    }

    /**
     * 设置标题内容
     *
     * @param titleResId
     */
    public void setTopbarTitle(int titleResId) {
        if (topTitleTextView != null) {
            topTitleTextView.setText(titleResId);
        }
    }

    /**
     * 设置标题文字颜色
     *
     * @param titleResId
     */
    public void setTitleTextColor(int titleResId) {
        if (topTitleTextView != null) {
            topTitleTextView.setTextColor(titleResId);
        }
    }

    /**
     * 设置标题内容 String类型
     *
     * @param title
     */
    public void setTopbarTitle(String title) {
        if (topTitleTextView != null) {
            topTitleTextView.setText(title);
        }
    }

    /**
     * 获取标题内容
     *
     * @return
     */
    public String getTopBarTitle() {
        if (topTitleTextView != null) {
            return topTitleTextView.getText().toString();
        }
        return "";
    }

    /**
     * 设置Mid txtview 右drables
     *
     * @param drawableId
     */
    public void setMidRightCompoundDrawables(int drawableId) {
        if (topTitleTextView != null) {
            Drawable drawable = getResources().getDrawable(drawableId);
            topTitleTextView.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);

        }
    }

    /**
     * 设置右边按钮的前景色
     *
     * @param resSrcId
     */
    public void setRightBtnResource(int resSrcId) {
        if (rightButton != null) {
            if (resSrcId > 0) {
                rightButton.setVisibility(View.VISIBLE);
                rightButton.setImageResource(resSrcId);

            } else {
                rightButton.setVisibility(View.GONE);
            }
        }
    }

    public void setRightBtnResource(int resSrcId, int padding) {
        if (rightButton != null) {
            if (resSrcId > 0) {
                rightButton.setVisibility(View.VISIBLE);
                rightButton.setImageResource(resSrcId);
                rightButton.setPadding(padding, padding, padding, padding);

            } else {
                rightButton.setVisibility(View.GONE);
            }
        }
    }

    public ImageButton getRightButton() {
        return rightButton;
    }

    public TextView getRightText() {
        return rightTextView;
    }

    public void setRightTextView(String rightValue) {
        rightTextView.setText(rightValue);
        rightTextView.setVisibility(VISIBLE);
    }

    /**
     * 根据 Postion参数，设置不同的位置组件的值（String，int ）
     * int && value
     *
     * @param object
     */
    public void setTextValue(Object object, int position) {
        TextView textView = null;
        switch (position) {
            case LEFT:
                textView = leftTextView;
                break;
            case MID:
                textView = topTitleTextView;
                break;
            case RIGHT:
                textView = rightTextView;
                break;
            default:
                break;
        }
        if (textView != null) {
            if (object != null) {
                if (object instanceof Integer) {
                    textView.setText(getResources().getString(
                            (Integer) object));
                } else if (object instanceof String) {
                    textView.setText((String) object);
                }
                textView.setVisibility(View.VISIBLE);
            } else {
                textView.setVisibility(View.GONE);
            }
        }
    }


    public void setRightTextEnable(boolean enable) {
        rightTextView.setEnabled(enable);
    }

    /**
     * 隐藏指定的TextView
     *
     * @param position
     */
    public void setTextViewHidden(int position) {
        TextView textView = null;
        switch (position) {
            case LEFT:
                textView = leftTextView;
                break;
            case MID:
                textView = topTitleTextView;

                break;
            case RIGHT:
                textView = rightTextView;
                break;
            default:
                break;
        }
        if (textView != null) {
            textView.setVisibility(GONE);
        }
    }


    /**
     * 设置drawable offset
     *
     * @param orientation     方向
     * @param resId           资源
     * @param drawablePadding 距离
     */
    public void setDrableResource(int orientation, int resId, int drawablePadding) {
        if (topTitleTextView != null) {
            switch (orientation) {
                case LEFT:
                    topTitleTextView.setCompoundDrawablesWithIntrinsicBounds(resId, 0, 0, 0);
                    break;
                case TOP:
                    topTitleTextView.setCompoundDrawablesWithIntrinsicBounds(0, resId, 0, 0);
                    break;
                case RIGHT:
                    topTitleTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, resId, 0);
                    break;
                case BOTTOM:
                    topTitleTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, resId);
                    break;
                default:
                    break;
            }
            topTitleTextView.setCompoundDrawablePadding(drawablePadding);
        }
    }

    /**
     * 添加自定义布局
     *
     * @param v
     */
    public void addRightContentLayout(View v) {
        if (rightContentLayout != null) {
            rightContentLayout.addView(v);
        }
    }

    public void clearRightContentLayout() {
        if (rightContentLayout != null) {
            rightContentLayout.removeAllViews();
        }
    }


    public void setOnRightLayoutListener(OnRightLayoutListener listener) {
        onRightLayoutListener = listener;
    }

    public void setonTopbarLeftLayoutListener(
            OnLeftLayoutListener listener) {
        onLeftLayoutListener = listener;
    }

    public void setonCenterListener(OnMidLayoutListener listener) {
        onMidLayoutListener = listener;
    }

    public void setonTopbarLeftLayoutListener() {

    }


    /**
     * 点击监听
     */
    public interface OnRightLayoutListener {
        void onRightClick();
    }

    public interface OnLeftLayoutListener {
        void onLeftClick();
    }

    public interface OnMidLayoutListener {
        void onMidClick();
    }

    final class ActionClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int i = v.getId();
            if (i == R.id.topbarLeftLinearLayout) {
                if (onLeftLayoutListener != null) {
                    onLeftLayoutListener.onLeftClick();
                }

            } else if (i == R.id.text_title) {
                if (onMidLayoutListener != null) {
                    onMidLayoutListener.onMidClick();
                }

            } else if (i == R.id.ic_comright_iv) {
                if (onRightLayoutListener != null) {
                    onRightLayoutListener.onRightClick();
                }

            } else if (i == R.id.ic_comright_tv) {
                if (onRightLayoutListener != null) {
                    onRightLayoutListener.onRightClick();
                }

            } else {
            }

        }
    }

}