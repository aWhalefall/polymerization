package com.polymerization.core.retrofit.convert;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
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
    public T convert(ResponseBody value) throws IOException {

        try {
            T response = adapter.fromJson(value.charStream());
//
//            if (response.errorCode == 0) {
//                return response.data;
//            } else {
//                // TODO: 2018/11/20 抛出异常 ，如果定义
//                throw new ServerResponseException(response.errorCode, response.errorMsg);
//            }
            if (((Response) response).data == null)
                ((Response) response).data = 1;
            return response;

        } finally {
            value.close();
        }

    }

}
