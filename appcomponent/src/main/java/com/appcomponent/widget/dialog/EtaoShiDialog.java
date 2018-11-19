/*
 * File Name: EtaoShiDialog.java 
 * History:
 * Created by LiBingbing on 2013-9-16
 */
package com.appcomponent.widget.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.wx.polymerization.appcomponent.R;

import java.util.Timer;
import java.util.TimerTask;


public class EtaoShiDialog extends Dialog {

    // ==========================================================================
    // Constants
    // ==========================================================================

    // ==========================================================================
    // Fields
    // ==========================================================================
    private Activity mActivity;
    protected EtaoShiDialogView mView;

    // ==========================================================================
    // Constructors
    // ==========================================================================
    public EtaoShiDialog(Activity activity) {
        super(activity);
        mActivity = activity;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setCanceledOnTouchOutside(false);
    }

    // ==========================================================================
    // Getters
    // ==========================================================================
    public Activity getActivity() {
        return mActivity;
    }

    public EtaoShiDialogView getDialogView() {
        return mView;
    }

    // ==========================================================================
    // Setters
    // ==========================================================================

    // ==========================================================================
    // Methods
    // ==========================================================================
    public static class Builder {

        private Activity mActivity;

        private EtaoShiDialog mDialog;

        private OnClickListener mPositiveClickListener;

        private OnClickListener mNeutralClickListener;

        private OnClickListener mNegativeClickListener;

        private OnCancelListener mCancelClickListener;

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
            mDialog = new EtaoShiDialog(activity);
            mDialog.mView = new EtaoShiDialogView(mActivity, w, h);
            mDialog.mView.setPositiveButtonText(android.R.string.ok);
            mDialog.mView.setPositiveButtonListener(new View.OnClickListener() {

                public void onClick(View v) {
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

        /**
         * 验证码构造模块
         */
        private EditText inputEt;
        private ImageView codeImg;
        private ImageView refreshImg;
        public RelativeLayout layout;

        /**
         * 短信验证码 图形CODE构造
         *
         * @param inputEt
         * @param codeImg
         * @param refreshImg
         * @param layout
         */
        public void setCodeGraphicBuilder(EditText inputEt, ImageView codeImg, ImageView refreshImg, RelativeLayout layout) {
            this.inputEt = inputEt;
            this.codeImg = codeImg;
            this.layout = layout;
            this.refreshImg = refreshImg;
        }

        /**
         * 购买 图形CODE 构造
         *
         * @param inputEt
         * @param refreshImg
         */
        public void setCodeBuyBuilder(EditText inputEt, ImageView refreshImg) {
            this.inputEt = inputEt;
            this.refreshImg = refreshImg;
        }

        /**
         * 获取图形CODE-短信模块专用
         */
        public void showSmsCodeErr() {
            if (layout == null && refreshImg == null && inputEt == null) return;
            layout.setVisibility(View.VISIBLE);
            refreshImg.performClick();
            inputEt.setText("");
            //三秒后消失
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (mActivity != null) {
                        mActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                layout.setVisibility(View.INVISIBLE);
                            }
                        });
                    }
                }
            }, 3000);
        }

        public void showBuyCodeErr() {
            refreshImg.performClick();
            inputEt.setText("");
        }

        public void dismiss() {
            if (mDialog != null) mDialog.dismiss();
        }

        /**
         * 支付协议特殊模块--------------⬇️start⬇️-----------------------
         * 支付协议构造属性
         */
        private EditText codeEt;
        private TextView errorTv, negativeBtn, positiveBtn;
        private RelativeLayout vcodeView;
        private RelativeLayout progressBar;

        /**
         * 银行卡协议支付，短信验证码 构造
         *
         * @param codeEt    输入验证码框
         * @param errorTv   错误文案提示
         * @param vcodeView 发送验证码控件（VcodeView），activity中调用需要强转
         */
        public void setAgreementPayCodeBuilder(EditText codeEt, TextView errorTv, RelativeLayout vcodeView, RelativeLayout progressBar, TextView negativeBtn, TextView positiveBtn) {
            this.codeEt = codeEt;
            this.layout = vcodeView;
            this.errorTv = errorTv;
            this.progressBar = progressBar;
            this.negativeBtn = negativeBtn;
            this.positiveBtn = positiveBtn;
        }

        public void showAgreementPayErrorMsg(String msg) {
            hindAgreementProgressBar();
            if (errorTv != null && !msg.isEmpty()) {
                errorTv.setVisibility(View.VISIBLE);
                errorTv.setText(msg);
            }
        }

        public void hindAgreementPayErrorMsg() {
            if (errorTv != null) {
                errorTv.setVisibility(View.GONE);
            }
        }

        public String getVerifyCodeFromEt() {
            if (codeEt != null) {
                return codeEt.getText().toString().trim();
            }
            return null;
        }

        public void showAgreementProgressBar() {
            if (progressBar != null) {
                hindAgreementPayErrorMsg();
                setBtnEnable(false);
                progressBar.setVisibility(View.VISIBLE);
            }
        }

        public void hindAgreementProgressBar() {
            if (progressBar != null) {
                progressBar.setVisibility(View.GONE);
                setBtnEnable(true);
            }
        }

        public void clearEt() {
            if (codeEt != null) {
                codeEt.setText("");
            }
        }

        public void setBtnEnable(boolean canClick) {
            if (negativeBtn != null && positiveBtn != null) {
                negativeBtn.setEnabled(canClick);
                positiveBtn.setEnabled(canClick);
                if (canClick) {
                    negativeBtn.setTextColor(ContextCompat.getColor(mActivity, R.color.color_FF6F00));
                    positiveBtn.setTextColor(ContextCompat.getColor(mActivity, R.color.color_FF6F00));
                } else {
                    negativeBtn.setTextColor(ContextCompat.getColor(mActivity, R.color.color_666666));
                    positiveBtn.setTextColor(ContextCompat.getColor(mActivity, R.color.color_666666));
                }
            }
        }


        /**
         * 支付协议特殊模块--------------⬆️end⬆️-----------------------
         */


        /**
         * end
         *
         * @param activity
         */

        public Builder(Context activity) {
            mActivity = (Activity) activity;
            mDialog = new EtaoShiDialog(mActivity);
            mDialog.mView = new EtaoShiDialogView(mActivity);
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

        public Builder setBackgroundColor(int resID) {
            mDialog.mView.setBackgroundColor(resID);
            return this;
        }

        // public Builder setTitleBackgroundColor(int color) {
        // mDialog.mView.setTitleBackgroundColor(color);
        // return this;
        // }
        //
        // public Builder setContainerBackgroundColor(int color) {
        // mDialog.mView.setContainerBackgroundColor(color);
        // return this;
        // }

        public Builder setVisibleAreaBackgroundResource(int resId) {
            mDialog.mView.setVisibleAreaBackgroudResource(resId);
            return this;
        }

        public Builder setVisibleAreaBackgroundColor(int color) {
            mDialog.mView.setVisibleAreaBackgroudColor(color);
            return this;
        }

        public Builder setBottomBackgroundColor(int color) {
            mDialog.mView.setBottomBackgroundColor(color);
            return this;
        }

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

        public Builder setTitle(CharSequence text) {
            if (null != text) {
                mDialog.mView.setTitle(text);
            }
            return this;
        }

        public Builder setTitleColor(int color) {
            mDialog.mView.setTitleTextColor(color);
            return this;
        }

        public Builder setTitleVisible(boolean b) {
            mDialog.mView.setTitleVisible(b);
            return this;
        }

        public Builder setButtonsVisible(boolean b) {
            mDialog.mView.setButtonsVisible(b);
            return this;
        }

        public Builder setCancelBottomViewVisible(boolean b) {
            mDialog.mView.setCancelBottomViewVisible(b);
            return this;
        }

        public Builder setPositiveButtonText(int textResId) {
            mDialog.mView.setPositiveButtonText(textResId);
            return this;
        }

        public Builder setPositiveButtonText(CharSequence text) {
            if (null != text) {
                mDialog.mView.setPositiveButtonText(text);
            }
            return this;
        }

        public Builder setPositiveButtonClickable(boolean clickable) {
            mDialog.mView.setPositiveButtonClickable(clickable);
            return this;
        }

        public Builder setPositiveButtonEnable(boolean enable) {
            mDialog.mView.setPositiveButtonEnable(enable);
            return this;
        }

        public Builder setPositiveButtonListener(
                OnClickListener l) {
            mPositiveClickListener = l;
            return this;
        }

        public Builder setPositiveButtonTextColor(int color) {
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

        public Builder setNeutralButtonListener(
                OnClickListener l) {
            mNeutralClickListener = l;
            return this;
        }

        public Builder setPositiveButtonVisible(boolean visible) {
            mDialog.mView.setPositiveButtonVisible(visible);
            return this;
        }

        public Builder setNegativeButtonVisible(boolean visible) {
            mDialog.mView.setNegativeButtonVisible(visible);
            return this;
        }

        public Builder setNegativeButtonText(int textResId) {
            mDialog.mView.setNegativeButtonText(textResId);
            return this;
        }

        public Builder setNegativeButtonText(CharSequence text) {
            if (null != text) {
                mDialog.mView.setNegativeButtonText(text);
            }
            return this;
        }

        public Builder setNegativeButtonListener(
                OnClickListener l) {
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

        public Builder setTextContent(CharSequence text) {
            if (null != text) {
                mDialog.mView.setTextContent(text);
            }
            return this;
        }

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

        public EtaoShiDialog create() {
            return mDialog;
        }


    }
    // ==========================================================================
    // Inner/Nested Classes
    // ==========================================================================
}
