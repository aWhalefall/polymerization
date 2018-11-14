package com.appcomponent.webview.parser;/**
 * Created by RoryHe on 16/12/5.
 */


import com.appcomponent.utils.StringUtil;
import com.appcomponent.webview.JsBridgeResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.safframework.log.L;


/**
 * @Author roryHe
 * @ClassName MsgParser
 * @CreateTIme 16/12/5 上午10:13
 * 用于push和js的消息解析
 */
public class MsgParser {

    private JSMsgParser jsMsgParser;
    private PushMsgParser pushMsgParser;
    private boolean isUseJsMsg = false, isUsePushMsg = false;

    public MsgParser(JSMsgParser jsMsgParser) {
        this.jsMsgParser = jsMsgParser;
        isUseJsMsg = true;
        isUsePushMsg = false;
    }

    public MsgParser(PushMsgParser pushMsgParser) {
        this.isUsePushMsg = true;
        this.isUseJsMsg = false;
        this.pushMsgParser = pushMsgParser;
    }

    public void parserMsg(String json) {
        if (jsMsgParser == null && isUseJsMsg) {
            L.e("js调用Android", "JSMsgParser==null !");
            return;
        }

        if (pushMsgParser == null && isUsePushMsg) {
            L.e("push调用Android", "PushMsgParser==null !");
            return;
        }

        if (StringUtil.isEmpty(json)) {
            L.e("push调用Android", "PushMsgParser==null !");
            return;
        }

        if (isUseJsMsg) {
            dealParserJsMsg(json);
        }

        if (isUsePushMsg) {
            dealParserPushMsg(json);
        }

    }

    /**
     * 处理js相关
     *
     * @param json
     */
    private void dealParserJsMsg(String json) {
        JsonParser parser = new JsonParser();
        try {
            JsonObject object = parser.parse(json).getAsJsonObject();
            JsBridgeResponse response = new JsBridgeResponse(object) {
                @Override
                public void dealJsBridge(String messageId, String version, String uniqueId, JsonObject param) {
                    L.e("js调用Android", "messageId===" + messageId + "---version===" + version +
                            "---uniqueId===" + uniqueId.toString() + "---param===" + param.toString());
                    switch (messageId) {
                        case "messageId_certificate_result":
                            jsMsgParser.certificate_result(param);
                            break;
                        case "messageId_tologin":
                            jsMsgParser.tologin(param);
                            break;
                        case "messageId_sendurl":
                            jsMsgParser.sendurl(param, uniqueId);
                            break;
                        case "messageId_share":
                            jsMsgParser.share(param, uniqueId);
                            break;
                        case "messageId_showsharebtn":
                            jsMsgParser.showShareBtn(param, uniqueId);
                            break;
                        case "messageId_toFinancial":
                            jsMsgParser.toFinancial(param);
                            break;
                        case "messageId_Home":
                            jsMsgParser.toHome(param);
                            break;
                        case "messageId_tonativeback":
                            jsMsgParser.toAutherActivity(param);
                            break;
                        case  "messageId_toTel":
                            jsMsgParser.toTel(param);
                            break;
                        case "messageId_toMessage":
                            jsMsgParser.toMessage(param);
                            break;
                        case "messageId_tonativerror":
                            jsMsgParser.toNativerror(param);
                            break;
                        case "messageId_toBrowser":
                            jsMsgParser.toBrowser(param);
                        default:
                            break;
                    }
                }
            };
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理push相关
     *
     * @param json
     */
    private void dealParserPushMsg(String json) {
        JsonParser parser = new JsonParser();
        try {
            JsonObject object = parser.parse(json).getAsJsonObject();
            JsBridgeResponse response = new JsBridgeResponse(object) {
                @Override
                public void dealJsBridge(String messageId, String version, String uniqueId, JsonObject param) {
                    switch (messageId) {
                        case "messageId_toIndex":
                            pushMsgParser.toIndex();
                            break;
                        case "messageId_toFinancial":
                            pushMsgParser.toFinancial();
                            break;
                        case "messageId_toFind":
                            pushMsgParser.toFind();
                            break;
                        case "messageId_toMine":
                            pushMsgParser.toMine();
                            break;
                        case "messageId_toPage":
                            pushMsgParser.toPage(param);
                            break;
                        default:
                            //默认做去首页的处理
                            pushMsgParser.toIndex();
                            break;
                    }
                }
            };
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
