package com.polymerization.core.retrofit.respond;

/**
 * Created by Zaifeng on 2018/2/28.
 * 返回结果封装
 */
//处理Response data返回null的情况
public class Response<T> {

    private int showapi_res_code; // 返回的code
    private T showapi_res_body; // 具体的数据结果
    private String showapi_res_error; // message 可用来返回接口的说明

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public T getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(T showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }
}
