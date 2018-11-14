package com.appcomponent.webview;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.webkit.JavascriptInterface;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.safframework.log.L;

import java.io.Serializable;

/**
 * Created by user on 2016/3/23.
 */

public class BankJavascriptBridge implements Serializable {
    private Activity mContext;
    private Handler mHandler;
    private int code;
    private String msg;

    public BankJavascriptBridge(String cardMobile, long order, String mSendAmount, long iscert, Context context, Handler handler) {
        this.mContext = (Activity) context;
        this.mHandler = handler;
    }

    @JavascriptInterface
    public void bridgeSendMessage(String jsonStr) {
        final String json = jsonStr;
        mHandler.post(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(mContext, "js调用Android" + json, Toast.LENGTH_SHORT).show();
                L.e("js调用Android", json);
                //TODO 进行json解析和逻辑处理
                JsonParser jP = new JsonParser();
                try {
                    JsonObject jPObject = jP.parse(json).getAsJsonObject();
                    if (jPObject.has("param")) {
                        JsonObject jsonObject = jPObject.get("param").getAsJsonObject();
                        if (jsonObject.has("code")) {
                            Long lCode = jsonObject.get("code").getAsLong();
                            String sCode = String.valueOf(lCode);
                            code = Integer.valueOf(sCode);
                        }
                        if (jsonObject.has("msg")) {
                            msg = jsonObject.get("msg").getAsString();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // TODO: 2018/11/14 暂时移除
//                if (!StringUtil.isEmpty(msg)) {
//                    Toaster.show(msg);
//                } else {
//                   Toaster.show("招行充值鉴权失败");
//                }
                switch (code) {
                    case 0: //鉴权成功
//                        Intent successI = new Intent();
//                        successI.putExtra("cardMobile", cardMobile);
//                        successI.putExtra("order", order);
//                        successI.putExtra("amount", mSendAmount);
//                        successI.putExtra("incert", iscert);
//                        successI.setClass(mContext, RechargeActivity.class);
                        // mContext.startActivity(successI);
                        if (mContext instanceof WebActivity) {
                            mContext.finish();
                        }
                        break;
                    default:
                        // TODO: 2016/6/30 逻辑以后完善
//                        Intent faileI = new Intent();
//                        faileI.setClass(mContext, RechargeActivity.class);
                        // mContext.startActivity(faileI);
                        if (mContext instanceof WebActivity) {
                            mContext.finish();
                        }
                        break;
                }
            }
        });
    }

}
