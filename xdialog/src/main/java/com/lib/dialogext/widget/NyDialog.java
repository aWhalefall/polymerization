/*
 * File Name: EtaoShiDialog.java 
 * History:
 * Created by LiBingbing on 2013-9-16
 */
package com.lib.dialogext.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ListAdapter;


public class NyDialog extends Dialog {


    private Activity mActivity;

    protected NyDialogView mView;


    public NyDialog(Activity activity) {
        super(activity);
        mActivity = activity;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setCanceledOnTouchOutside(false);
    }


    public NyDialog(Activity activity, int themeResId) {
        super(activity, themeResId);
        mActivity = activity;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setCanceledOnTouchOutside(false);
    }


    public Activity getActivity() {
        return mActivity;
    }

    public NyDialogView getDialogView() {
        return mView;
    }

    public static class Builder {

        private Activity mActivity;

        private NyDialog mDialog;

        private DialogInterface.OnClickListener mPositiveClickListener;

        private DialogInterface.OnClickListener mNeutralClickListener;

        private DialogInterface.OnClickListener mNegativeClickListener;

        private DialogInterface.OnCancelListener mCancelClickListener;


        /**
         * 改变diglog整体大小
         * 这个构造的positive需要自己去dissmis-dialog
         *
         * @param activity
         * @param w
         * @param h
         */
        public Builder(Activity activity, int w, int h) {
            mActivity = activity;
            mDialog = new NyDialog(activity);
            mDialog.mView = new NyDialogView(mActivity, w, h);
            mDialog.mView.setPositiveButtonText(android.R.string.ok);
            mDialog.mView.setPositiveButtonListener(new View.OnClickListener() {

                public void onClick(View v) {
                    if (null != mPositiveClickListener) {
                        mPositiveClickListener.onClick(mDialog,
                                DialogInterface.BUTTON_POSITIVE);
                    }
                }

            });
            mDialog.mView.setNeutralButtonText("");
            mDialog.mView.setNeutralButtonListener(new View.OnClickListener() {

                public void onClick(View v) {
                    mDialog.dismiss();
                    if (null != mNeutralClickListener) {
                        mNeutralClickListener.onClick(mDialog,
                                DialogInterface.BUTTON_NEUTRAL);
                    }
                }

            });
            mDialog.mView.setNeutralButtonVisible(false);
            mDialog.mView.setNegativeButtonText(android.R.string.cancel);
            mDialog.mView.setNegativeButtonListener(new View.OnClickListener() {

                public void onClick(View v) {
                    mDialog.dismiss();
                    if (null != mNegativeClickListener) {
                        mNegativeClickListener.onClick(mDialog,
                                DialogInterface.BUTTON_NEGATIVE);
                    }
                }

            });

            mDialog.mView.setCancelBottomViewListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDialog.dismiss();
                    if (null != mCancelClickListener) {
                        mCancelClickListener.onCancel(mDialog);
                    }
                    if (null != mNegativeClickListener) {
                        mNegativeClickListener.onClick(mDialog,
                                DialogInterface.BUTTON_NEUTRAL);
                    }
                }
            });

            mDialog.setContentView(mDialog.mView);
            mDialog.getWindow().setLayout(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
        }


        public void dismiss() {
            if (mDialog != null) mDialog.dismiss();
        }

        /**
         * end
         *
         * @param activity
         */

        public Builder(Context activity) {
            mActivity = (Activity) activity;
            mDialog = new NyDialog(mActivity);
            mDialog.mView = new NyDialogView(mActivity);
            initDialogView();
        }

        private void initDialogView() {
            mDialog.mView.setPositiveButtonText(android.R.string.ok);
            mDialog.mView.setPositiveButtonListener(new View.OnClickListener() {

                public void onClick(View v) {
                    mDialog.dismiss();
                    if (null != mPositiveClickListener) {
                        mPositiveClickListener.onClick(mDialog,
                                DialogInterface.BUTTON_POSITIVE);
                    }
                }

            });
            mDialog.mView.setNeutralButtonText("dd");
            mDialog.mView.setNeutralButtonListener(new View.OnClickListener() {

                public void onClick(View v) {
                    mDialog.dismiss();
                    if (null != mNeutralClickListener) {
                        mNeutralClickListener.onClick(mDialog,
                                DialogInterface.BUTTON_NEUTRAL);
                    }
                }

            });
            mDialog.mView.setNeutralButtonVisible(false);
            mDialog.mView.setNegativeButtonText(android.R.string.cancel);
            mDialog.mView.setNegativeButtonListener(new View.OnClickListener() {

                public void onClick(View v) {
                    mDialog.dismiss();
                    if (null != mNegativeClickListener) {
                        mNegativeClickListener.onClick(mDialog,
                                DialogInterface.BUTTON_NEGATIVE);
                    }
                }

            });

            mDialog.mView.setCancelBottomViewListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDialog.dismiss();
                    if (null != mCancelClickListener) {
                        mCancelClickListener.onCancel(mDialog);
                    }
                    if (null != mNegativeClickListener) {
                        mNegativeClickListener.onClick(mDialog,
                                DialogInterface.BUTTON_NEUTRAL);
                    }
                }
            });


            mDialog.setContentView(mDialog.mView);
            mDialog.getWindow().setLayout(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
        }

        public Builder(Context activity, int theme) {
            mActivity = (Activity) activity;
            mDialog = new NyDialog(mActivity, theme);
            mDialog.mView = new NyDialogView(mActivity);
            initDialogView();
        }

        public Builder(Context activity, int w, int h, int theme) {
            mActivity = (Activity) activity;
            mDialog = new NyDialog(mActivity, theme);
            mDialog.mView = new NyDialogView(mActivity, w, h);
            initDialogView();
        }

        public Builder setBackgroundColor(int resID) {
            mDialog.mView.setBackgroundColor(resID);
            return this;
        }

        /**
         * set dialog背景
         *
         * @param resId recommond shap type
         * @return
         */
        public Builder setVisibleAreaBackgroundResource(int resId) {
            mDialog.mView.setVisibleAreaBackgroudResource(resId);
            return this;
        }

        public Builder setVisibleAreaBackgroundColor(int color) {
            mDialog.mView.setVisibleAreaBackgroudColor(color);
            return this;
        }


        public Builder setBottomViableAreaBackgroundColor(int color) {
            mDialog.mView.setBottomBackgroundColor(color);
            return this;
        }

        /**
         * recommond shap Type
         *
         * @param resId
         * @return
         */
        public Builder setBtnNegativeBackgroundResource(int resId) {
            mDialog.mView.setBtnNegativeBackgroundResource(resId);
            return this;
        }

        public Builder setBtnPositiveBackgroundResource(int resId) {
            mDialog.mView.setBtnPositiveBackgroundResource(resId);
            return this;
        }

        public Builder setBtnNeutralBackgroundResource(int resId) {
            mDialog.mView.setBtnNeutralBackgroundResource(resId);
            return this;
        }

        public Builder setTitle(int textResId) {
            mDialog.mView.setTitle(textResId);
            return this;
        }

        /**
         * @param text
         * @return
         */
        public Builder setTitle(CharSequence text) {
            if (null != text) {
                mDialog.mView.setTitle(text);
            }
            return this;
        }

        /**
         * set top title  textview color
         *
         * @param color title text color
         * @return
         */
        public Builder setTitleColor(int color) {
            mDialog.mView.setTitleTextColor(color);
            return this;
        }

        public Builder setTitleVisible(boolean b) {
            mDialog.mView.setTitleVisible(b);
            return this;
        }

        /**
         * @param b
         * @return
         */
        public Builder setBottomViableAreaVisible(boolean b) {
            mDialog.mView.setButtonsVisible(b);
            return this;
        }

        /**
         * Set whether the CancelBottomVie is visible. The default is true.
         *
         * @param visible
         * @return
         */
        public Builder setCancelBottomViewVisible(boolean visible) {
            mDialog.mView.setCancelBottomViewVisible(visible);
            return this;
        }

        public Builder setPositiveBtnText(int textResId) {
            mDialog.mView.setPositiveButtonText(textResId);
            return this;
        }

        public Builder setPositiveBtnText(CharSequence text) {
            if (null != text) {
                mDialog.mView.setPositiveButtonText(text);
            }
            return this;
        }

        public Builder setPositiveBtnClickable(boolean clickable) {
            mDialog.mView.setPositiveButtonClickable(clickable);
            return this;
        }

        public Builder setPositiveBtnEnable(boolean enable) {
            mDialog.mView.setPositiveButtonEnable(enable);
            return this;
        }

        /**
         * set confirm btn listener
         *
         * @param l interface callback
         * @return
         */
        public Builder setPositiveButtonListener(
                DialogInterface.OnClickListener l) {
            mPositiveClickListener = l;
            return this;
        }

        /**
         * 确认按钮 color
         *
         * @param color
         * @return
         */
        public Builder setPositiveBtnTextColor(int color) {
            mDialog.mView.setButtonPositiveTextColor(color);
            return this;
        }

        public Builder setNeutralButtonVisible(boolean visible) {
            mDialog.mView.setNeutralButtonVisible(visible);
            return this;
        }

        public Builder setNegativeButtonTextColor(int textColor) {

            mDialog.mView.setNegativeButtonTextColor(textColor);

            return this;
        }


        public Builder setNeutralButtonText(int textResId) {
            mDialog.mView.setNeutralButtonText(textResId);
            return this;
        }

        public Builder setNeutralButtonText(CharSequence text) {
            if (null != text) {
                mDialog.mView.setNeutralButtonText(text);
            }
            return this;
        }

        /**
         * 中性的按钮
         *
         * @param l
         * @return
         */
        public Builder setNeutralButtonListener(
                DialogInterface.OnClickListener l) {
            mNeutralClickListener = l;
            return this;
        }

        /**
         * et cancelBtn is can visiable,default is visiable
         *
         * @param visible
         * @return
         */
        public Builder setNegativeButtonVisible(boolean visible) {
            mDialog.mView.setNegativeButtonVisible(visible);
            return this;
        }

        /**
         * set cancel btn textValue
         *
         * @param textResId Text资源id
         * @return
         */
        public Builder setNegativeButtonText(int textResId) {
            mDialog.mView.setNegativeButtonText(textResId);
            return this;
        }

        /**
         * ['negətɪv]  取消按钮
         *
         * @param text
         * @return
         */
        public Builder setNegativeButtonText(CharSequence text) {
            if (null != text) {
                mDialog.mView.setNegativeButtonText(text);
            }
            return this;
        }

        /**
         * 取消按钮监听回调
         *
         * @param l
         * @return
         */
        public Builder setNegativeButtonListener(
                DialogInterface.OnClickListener l) {
            mNegativeClickListener = l;
            return this;
        }

        public Builder setTextContent(int textResId) {
            mDialog.mView.setTextContent(textResId);
            return this;
        }

        public Builder setTextContent(int textResId, boolean scrollable) {
            mDialog.mView.setTextContent(textResId, scrollable);
            return this;
        }

        /**
         * 添加内容到的中间自定义区域
         *
         * @param text
         * @return
         */
        public Builder setTextContent(CharSequence text) {
            if (null != text) {
                mDialog.mView.setTextContent(text);
            }
            return this;
        }

        /**
         * 中间内容区域
         *
         * @param text       内容
         * @param scrollable 超出范围是否可以滚动
         * @return
         */
        public Builder setTextContent(CharSequence text, boolean scrollable) {
            if (null != text) {
                mDialog.mView.setTextContent(text, scrollable);
            }
            return this;
        }

        public Builder setListContent(ListAdapter contentAdapter) {
            mDialog.mView.setListContent(contentAdapter);
            return this;
        }

        /**
         * 中间内容区域
         *
         * @param //view
         * @return
         */
        public Builder setContentView(View v) {
            mDialog.mView.setContentView(v);
            return this;
        }

        //解决：申请转让下面灰色的线也是黄色的。
        public Builder setTitleDividerColor(int v) {
            mDialog.mView.setTitleDividerColor(v);
            return this;
        }


        public Builder setBottomDivideColor(int color) {
            mDialog.mView.setBottomDivideColor(color);
            return this;
        }

        public Builder setContainerBackground(int color) {
            mDialog.mView.setContainerBackground(color);
            return this;
        }

        public Builder setContainerBackgroundResource(int color) {
            mDialog.mView.setContainerBackgroundResource(color);
            return this;
        }

        public Builder setmBottomDividerVisiable(int visiable) {
            mDialog.mView.setmBottomDividerVisiable(visiable);
            return this;
        }

        public Builder setBottomViewPadding(int l, int t, int r, int b) {
            mDialog.mView.setButtonsPadding(l, t, r, b);
            return this;
        }


        public Builder setOnDismissListener(OnDismissListener l) {
            mDialog.setOnDismissListener(l);
            return this;
        }

        public Builder setmImgCancelShow() {
//            mDialog.mView.setmImgCancelShow();
//            mDialog.mView.setmImgCancelListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mDialog.dismiss();
//                }
//            });
            return this;
        }

        public NyDialog create() {
            return mDialog;
        }


    }

}
