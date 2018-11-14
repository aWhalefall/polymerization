package com.appcomponent.webview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.webkit.JavascriptInterface;

import com.appcomponent.webview.parser.JSMsgParser;
import com.appcomponent.webview.parser.MsgParser;
import com.google.gson.JsonObject;
import com.safframework.log.L;

import java.io.Serializable;

import utils.sptool.SpTool;

/**
 * Created by rory on 2016/10/11.
 */

public class WebJsBridge implements Serializable {
    private static final String TAG = WebJsBridge.class.getSimpleName();

    private Activity mActivity;
    private Handler mhHandler;
    private Intent dataIntent;
    private SpTool spTool;

    public WebJsBridge(Context context, Handler handler, Intent dataIntent) {
        this.mActivity = (Activity) context;
        this.mhHandler = handler;
        this.dataIntent = dataIntent;
        if (this.dataIntent != null)
            L.d(TAG, "this js bridge get intent data success");
        else
            L.d(TAG, "this js bridge get intent data null or fail");
    }


    public WebJsBridge(Context context) {
        this.mActivity = (Activity) context;
    }

    @JavascriptInterface
    public void bridgeSendMessage(String jsonStr) {
        final String json = jsonStr;
        mhHandler.post(new Runnable() {
            @Override
            public void run() {
                L.d("js调用Android", json);
                parserMsg(json);
            }
        });
    }

    private void parserMsg(String json) {
        MsgParser parser = new MsgParser(new JSMsgParser() {
            @Override
            public void certificate_result(JsonObject param) {
                certificate(param);
            }

            @Override
            public void tologin(JsonObject param) {
                toApplogin(param);
            }

            @Override
            public void sendurl(JsonObject param, String uniqueId) {
                // sendAppurl(param, uniqueId);
            }

            @Override
            public void share(JsonObject param, String uniqueId) {
               // wxshare(param, uniqueId);

            }

            @Override
            public void showShareBtn(JsonObject param, String uniqueId) {
              //  showWxShareBtn(param, uniqueId);
            }

            @Override
            public void toFinancial(JsonObject param) {
               // toHomeFinancial(param);
            }

            @Override
            public void toHome(JsonObject param) {
               // toHomepage(param);
            }

            @Override
            public void toAutherActivity(JsonObject param) {
                goAutherActivity(param);
            }

            @Override
            public void toTel(JsonObject param) {
               // toTelephone(param);
            }

            @Override
            public void toMessage(JsonObject param) {
               // toSendMessage(param);
            }

            @Override
            public void toNativerror(JsonObject param) {
               // toNativefromerror(param);
            }

            @Override
            public void toBrowser(JsonObject param) {
               // toBrowserView(param);
            }


        });
        parser.parserMsg(json);
    }

    /**
     * 跳转浏览器
     *
     * @param param
     */
    private void toBrowserView(JsonObject param) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        String url = "";
        if (param.has("url")) url = param.get("url").getAsString();
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        mActivity.startActivity(intent);
    }

    private void toNativefromerror(JsonObject param) {
        mActivity.finish();
    }

    /**
     * 调用发送短信功能
     *
     * @param param
     */
    private void toSendMessage(JsonObject param) {
        L.d("js调用Android", "toSendMessage");

        //   }
    }

    /**
     * 调用拨打电话功能
     *
     * @param param
     */
    private void toTelephone(JsonObject param) {
//        WebPhoneBean bean = new WebPhoneBean();
//        if (param.has("tel")) bean.tel = param.get("tel").getAsString();
//        if (!StringUtil.isEmpty(bean.tel)) {
//            Intent intent = new Intent(Intent.ACTION_CALL);
//            intent.setData(Uri.parse("tel:" + bean.tel));
//
//            mActivity.startActivity(intent);
//        }
    }


    private void toHomepage(JsonObject param) {
//        Logger.d("js调用Android", "toHome");
//        Intent intent = new Intent();
//        //intent.setClass(mActivity, MainActivity.class);
//        mActivity.startActivity(intent);
//        mActivity.finish();
    }

    private void goAutherActivity(JsonObject param) {
//        Logger.d("js调用Android", "toAutherActivity");
//        Intent intent = new Intent();
//        intent.setClass(mActivity, AutherActivity.class);
//        mActivity.setResult(111);
//        mActivity.finish();
    }

    private void toHomeFinancial(JsonObject param) {
//        Logger.d("js调用Android", "toHomeFinancial");
//        Intent intent = new Intent();
//        intent.setClass(mActivity, ProductListActivity.class);
//        mActivity.startActivity(intent);
//        mActivity.finish();
    }

//    private void showWxShareBtn(final JsonObject param, final String uniqueId) {
//        Logger.d("js调用Android", "ShowShareBtn -- parse param");
//        ImageView imgBtn = ((WebActivity) mActivity).getImg_right();
//        imgBtn.setImageResource(R.drawable.icon_more);
//        imgBtn.setScaleType(ImageView.ScaleType.CENTER);
//        imgBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                wxshare(param, uniqueId);
//            }
//        });
//        imgBtn.setVisibility(View.VISIBLE);
//    }

    private void wxshare(String shareUrl) {
//        UMImage image = new UMImage(mActivity, R.drawable.icon_share_daka);//资源文件
//        ShareDialogFragment.shareMsg((BaseActivity)mActivity
//                ,"【海豚记账福利】早起打卡赢现金红包，最高99元!快到碗里来"
//                , "早起打卡，每日投入一元，早上5点到8点来打卡，养成好习惯，还能转到收益"
//                , shareUrl
//                , image);
    }

    private void certificate(JsonObject param) {
//        if (param == null) return;
//        WebBankBean bean = new WebBankBean();
//        if (param.has("msg")) bean.setMsg(param.get("msg").getAsString());
//        if (param.has("code")) bean.setCode(param.get("code").getAsLong());
//        Logger.d("js调用Android", "certificate_result -- parse param");
//        if (!StringUtil.isEmpty(bean.getMsg())) {
//            Toaster.show(bean.getMsg());
//        } else {
//            Toaster.show("招行充值鉴权失败");
//        }
//        if (mActivity instanceof WebActivity) mActivity.finish();
//        Logger.d("js调用Android", "certificate_result -- finish");
    }

    private void toApplogin(JsonObject param) {
//        Logger.d("js调用Android", "tologin -- parse param==" + param.toString());
//        //TODO  登录还要回到当前页面，目前需要登录页面和从哪来到哪里去的业务完善后完成
//        String loadUrl = ((WebActivity) mActivity).getWebView().getUrl();
//
//        ArouterHelper.startActivity(PathConfig.LOGIN_ACTIVITY);
////        intent.putExtra(ComKey.WEB_LOGIN_TYPE, ComKey.WEB_LOGIN_TYPE);
////        intent.putExtra(ComKey.WEB_URL, loadUrl);
////        mActivity.startActivity(intent);
//        mActivity.finish();
    }

    public void sendAppurl(String url, final String phoneNumber, String platformId, String planId) {
//        //dpajsbridge://rebate/register?platformId=1&planId=2
//        ((BaseActivity) mActivity).request().jsBridgeLogic(phoneNumber, platformId, planId).enqueue(
//                new Callback<JsDataEntity>() {
//            @Override
//            public void onResponse(Call<JsDataEntity> call, Response<JsDataEntity> response) {
//                if(response.code()==200) {
//                    SpTool spTool = SpManager.getCommonSp(AppConfig.config.SP_User);
//                    spTool.putString(ConstanPool.JSBRIDGE_PHONE, phoneNumber);
//                    Intent advertiseIntent = new Intent();
//                    advertiseIntent.putExtra(ConstanPool.WEB_URL, response.body().rebateUrl);
//                    advertiseIntent.setClass(mActivity, WebActivity.class);
//                    mActivity.startActivity(advertiseIntent);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<JsDataEntity> call, Throwable t) {
//                Toaster.show(t.getMessage());
//            }
//        });


    }

    private void useJsFunc(final String funcName, String uid, String returnJson) {
        final String[] params = new String[2];
        params[0] = "\"" + uid + "\"";
        params[1] = returnJson;
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                useJsFunc(funcName, params);
            }
        });
    }

    private void useJsFunc(String funcName, String... params) {
        String param = "";
        for (int i = 0; i < params.length; i++) {
            if (i == params.length)
                param += params[i];
            else
                param += params[i] + ",";

        }
        if (param.endsWith(",")) param = param.substring(0, param.length() - 1);
        String fuc = "javascript:" + funcName + "(" + param + ")";
        ((WebActivity) mActivity).getWebView().loadUrl(fuc);
        L.d("js调用Android", "----请求调用js方法:----" + fuc);
    }


    public void showJsBridgeDialog(final String url) {
        //dpajsbridge://DailyBonusToPay?paytype=weixin
        //dpajsbridge://DailyBonusToDaka  手动签到标示
        //dpajsbridge://ShareToFriend?url=https://account.ftoutiao.cn/weixin/promotion/getup?appname=null


//        if (StringUtil.isEmpty(url))
//            return;
//
//        //手动触发打卡标示
//        if (url.contains("DailyBonusToDaka")) {
//            handPunch();
//            return;
//        }
//
//        Uri uri = Uri.parse(url);
//        final String paytype = uri.getQueryParameter("paytype");
//        if (StringUtil.isNotEmpty(paytype)) {
//            ComDialogUtils.wxDilog(mActivity, new DialogInterface.OnDismissListener() {
//                @Override
//                public void onDismiss(DialogInterface dialog) {
//
//                }
//            }, new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    WxPay(paytype);
//                }
//            });
//
//            return;
//        }
//        final String shareUrl = uri.getQueryParameter("url");
//        if (StringUtil.isNotEmpty(shareUrl)) {
//             wxshare(shareUrl);
//            return;
//        }
//        final String platformId = uri.getQueryParameter("platformId");
//        final String planId = uri.getQueryParameter("planId");
//        final String again_flag = uri.getQueryParameter("again_flag");
//        final String typ = uri.getQueryParameter("type");
//
//
//        SpTool spTool = SpManager.getCommonSp(AppConfig.config.SP_User);
//        spTool.getString(ConstanPool.JSBRIDGE_PHONE,"");
//        try {
//            Context mContext = mActivity;
//            String titleValue = "";
//            if (typ.equals("2")) {
//                titleValue = "登记办理信用卡的手机号";
//            } else {
//                titleValue = again_flag.equals("0") ? "登记首投投资手机号" : "登记复投投资手机号";
//            }
//            final View inflateView = View.inflate(mContext, R.layout.view_bridge_dialog, null);
//
//            final Dialog mLoadingDialog = new Dialog(mContext, com.acmenxd.frame.R.style.jsbridge_dialog);
//            mLoadingDialog.setContentView(inflateView);
//            WindowManager.LayoutParams wl = mLoadingDialog.getWindow().getAttributes();
//
//            wl.width = DeviceUtils.getScreenX(mContext) - (int) ((BaseActivity) mContext).dp2px(70f);
//            wl.height = (int) ((BaseActivity) mContext).dp2px(307f);
//            mLoadingDialog.getWindow().setAttributes(wl);
//            mLoadingDialog.setCancelable(true);
//            mLoadingDialog.setCanceledOnTouchOutside(true);
//            inflateView.findViewById(R.id.img_close).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mLoadingDialog.dismiss();
//                }
//            });
//            TextView textTipsw = inflateView.findViewById(R.id.txt_tips);
//            TextView textView = inflateView.findViewById(R.id.txt_title);
//            CheckBox checkAgree = inflateView.findViewById(R.id.check_agree);
//            final Button btn = inflateView.findViewById(R.id.btn_action);
//            if (platformId.equals("2")) {
//                textTipsw.setText("为了确保正常返现，请输入的您首次办理信用卡的手机号");
//            }
//            inflateView.findViewById(R.id.tv_term).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mLoadingDialog.dismiss();
//                    Intent advertiseIntent = new Intent();
//                    advertiseIntent.putExtra(ConstanPool.WEB_URL, ConstanPool.H5);
//                    advertiseIntent.setClass(mActivity, WebActivity.class);
//                    mActivity.startActivity(advertiseIntent);
//                }
//            });
//            checkAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    btn.setEnabled(isChecked);
//                }
//            });
//            textView.setText(titleValue);
//            btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mLoadingDialog.dismiss();
//                    EditText editText = inflateView.findViewById(R.id.edi_input_number);
//                    String editerValue = editText.getText().toString().trim();
//                    if (StringUtil.isEmpty(editerValue) || !StringUtil.isPhoneNumber(editerValue)) {
//                        Toaster.show("请输入正确的手机号码！");
//                    } else {
//                        sendAppurl(url, editerValue, platformId, planId);
//                    }
//
//                }
//            });
//            mLoadingDialog.show();
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

//
//    /**
//     * 微信的支付相关
//     *
//     * @param paytype
//     */
//    private void WxPay(String paytype) {
//        payApi = WXAPIFactory.createWXAPI(mActivity, ConstanPool.WXPAY_APPID);
//        payApi.registerApp(ConstanPool.WXPAY_APPID);
//        DateTime dateTime = new DateTime();
//        String strRand = "";
//        for (int i = 0; i < 8; i++) {
//            strRand += String.valueOf((int) (Math.random() * 10));
//        }
//
//        String srcid = dateTime.toString("yyyyMMddHHmmssSSS") + strRand;
//        String item = "1_1001_1_" + dateTime.getMillis();
//        ((BaseActivity) mActivity).request().submitOrder(item, "app", srcid, dateTime.getMillis()).enqueue(
//                new Callback<SubOrderBo>() {
//                    @Override
//                    public void onResponse(Call<SubOrderBo> call, Response<SubOrderBo> response) {
//                        if (response.code() == 200 && response.body().getCode() == 0) {
//                            prePay(response.body());
//                        } else {
//                            Toaster.show(response.body().getMsg());
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<SubOrderBo> call, Throwable t) {
//                        Toaster.show(t.getMessage());
//                    }
//                });
//    }
//
//    /**
//     * 预支付
//     *
//     * @param response
//     */
//    private void prePay(SubOrderBo response) {
//        spTool = SpManager.getCommonSp(AppConfig.config.SP_User);
//        spTool.putLong(ConstanPool.PAYORDER_ID, response.orderId);
//        ((BaseActivity) mActivity).request().prePay(
//                String.valueOf(response.orderId),
//                "weixin",
//                SystemClock.currentThreadTimeMillis()
//        ).enqueue(new Callback<PrePayBo>() {
//                    @Override
//                    public void onResponse(Call<PrePayBo> call, Response<PrePayBo> response) {
//                        if (response.code() == 200 && response.body().getCode() == 0) {
//                            wxpay(response.body());
//
//                        }else {
//                            Toaster.show(response.body().getMsg());
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<PrePayBo> call, Throwable t) {
//                        Toaster.show(t.getMessage());
//                    }
//                });
//    }
//
//    /**
//     * 发起微信支付
//     *
//     * @param body
//     */
//    private void wxpay(PrePayBo body) {
//        String appid = ConstanPool.WXPAY_APPID;
//        String partnerid = "1490856692";
//        String prepayid = body.prepayId;
//        String noncest = UUID.randomUUID().toString().replace("-", "");
//        String timestamp = String.valueOf(body.time);
//
//        spTool.putString(ConstanPool.PREPAYID, prepayid);
//        spTool.putString(ConstanPool.PAYID, body.payId);
//        SortedMap<Object, Object> parameters = new TreeMap();
//        parameters.put("partnerid", partnerid);
//        parameters.put("package", "Sign=WXPay");
//        parameters.put("appid", appid);
//        parameters.put("prepayid", prepayid);
//        parameters.put("noncestr", noncest);
//        parameters.put("timestamp", timestamp);
//
//        String mySign = WxMd5Utils.createSign("UTF-8", parameters);
//
//        PayReq req = new PayReq();
//        req.partnerId =partnerid;
//        req.packageValue = "Sign=WXPay";
//        req.appId = ConstanPool.WXPAY_APPID;
//        req.prepayId = body.prepayId;
//        req.nonceStr = noncest;
//        req.timeStamp = timestamp;
//        req.sign = mySign;
//
//        payApi.sendReq(req);
//    }


}
