package com.appcomponent.webview.parser;


import com.google.gson.JsonObject;

/**
 * Created by RoryHe on 16/12/5.
 * push消息实现
 */

public interface PushMsgParser {

    void toIndex();

    void toFinancial();

    void toFind();

    void toMine();

    void toPage(JsonObject param);
}
