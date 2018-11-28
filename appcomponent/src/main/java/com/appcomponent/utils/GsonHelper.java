package com.appcomponent.utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;

public class GsonHelper {

    private static Gson sGson;

    private GsonHelper() {
    }

    public static synchronized Gson getGsonInstance() {
        if (sGson == null) {
            sGson = new Gson();
        }
        return sGson;
    }

    private static <T> T fromJson(String json, Type typeOfT) {
        try {
            return getGsonInstance().fromJson(json, typeOfT);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T extends Object> T getClassFromJsonString(String data, Class<T> clazz) {
        return fromJson(data, clazz);
    }

}