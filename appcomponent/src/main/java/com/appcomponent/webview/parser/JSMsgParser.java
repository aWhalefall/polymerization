package com.appcomponent.webview.parser;


import com.google.gson.JsonObject;

/**
 * Created by RoryHe on 16/12/5.
 */

public interface JSMsgParser {

    void certificate_result(JsonObject param);

    void tologin(JsonObject param);

    void sendurl(JsonObject param, String uniqueId);

    void share(JsonObject param, String uniqueId);

    void showShareBtn(JsonObject param, String uniqueId);

    void toFinancial(JsonObject param);

    void toHome(JsonObject param);

    void toAutherActivity(JsonObject param);

    void toTel(JsonObject param);

    void toMessage(JsonObject param);

    void toNativerror(JsonObject param);

    void toBrowser(JsonObject param);
}
