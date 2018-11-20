package com.polymerization.video.callback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 统一过滤机制。 200 正确，异常<Error><Exception>
 *     1.调用方法的地方制定泛型类型，请求返回地方
 * @param <T>
 */
public class RetrofitCallBack<T> implements Callback<T> {
    @Override
    public void onResponse(Call<T> call, Response<T> response) {


    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {

    }
}
