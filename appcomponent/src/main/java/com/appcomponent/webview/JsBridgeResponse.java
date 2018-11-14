package com.appcomponent.webview;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Created by rory on 2016/10/11.
 */

public abstract class JsBridgeResponse {

    private JsonObject obj;
    private String messageId;
    private String uniqueId;
    private String version;
    private JsonObject param;

    public JsBridgeResponse(JsonObject object) throws Exception {
        this.obj = object;
        parse();
    }

    public void parse() {
        try {
            if (obj.has("version") && !obj.get("version").isJsonNull())
                setVersion(obj.get("version").getAsString());
            else
                setVersion("-1");
        } catch (Exception e) {
            setVersion("-1");
        }


        try {
            if (obj.has("uniqueId") && !obj.get("uniqueId").isJsonNull())
                setUniqueId(obj.get("uniqueId").getAsString());
            else
                setUniqueId("-1");
        } catch (Exception e) {
            setUniqueId("-1");
        }


        try {
            if (obj.has("messageId") && !obj.get("messageId").isJsonNull())
                setMessageId(obj.get("messageId").getAsString());
            else
                setMessageId("-1");
        } catch (Exception e) {
            setMessageId("-1");
        }


        if (obj.has("param") && !obj.get("param").isJsonNull()) {
            try {
                JsonObject param = obj.get("param").getAsJsonObject();
                setParam(param);
            } catch (Exception e) {
                e.printStackTrace();
                String json = obj.get("param").getAsString();
                JsonParser parser = new JsonParser();
                JsonObject jsonObject = parser.parse(json).getAsJsonObject();
                setParam(jsonObject);
            }
        } else
            setParam(null);

        dealJsBridge(messageId, version, uniqueId, param);
    }


    public abstract void dealJsBridge(String messageId, String version, String uniqueId, JsonObject param);

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public JsonObject getParam() {
        return param;
    }

    public void setParam(JsonObject param) {
        this.param = param;
    }
}
