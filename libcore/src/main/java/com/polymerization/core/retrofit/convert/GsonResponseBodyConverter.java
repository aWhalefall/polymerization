package com.polymerization.core.retrofit.convert;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.polymerization.core.retrofit.convert.exception.ServerResponseException;
import com.polymerization.core.retrofit.respond.Response;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Author: yangweichao
 * Date:   2018/11/20 11:39 AM
 * Description: https://blog.csdn.net/qq_20521573/article/details/70991850
 */


final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, Object> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public Object convert(ResponseBody value) throws IOException {

        try {
            Response response = (Response) adapter.fromJson(value.charStream());

            if (response.getShowapi_res_code() == 0) {
                return response.getShowapi_res_body();
            } else {
                // TODO: 2018/11/20 抛出异常 ，如果定义
                throw new ServerResponseException(response.getShowapi_res_code(), response.getShowapi_res_error());
            }

        } finally {
            value.close();
        }

    }

}
