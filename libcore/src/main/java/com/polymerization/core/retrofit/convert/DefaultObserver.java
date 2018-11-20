package com.polymerization.core.retrofit.convert;

import com.safframework.log.L;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * 网络处理方法二
 * @param <T>
 */
public abstract class DefaultObserver<T> implements Observer<T> {


    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T response) {
        onSuccess(response);
        onFinish();
    }


    @Override
    public void onComplete() {

    }

    /**
     * 成功回调
     *
     * @param response
     */
    abstract void onSuccess(T response);


    public void onError(int code, String errorMsg) {
        //区分两种情况。只需要提示，提示和业务，业务
    }

    @Override
    public void onError(Throwable e) {
        //todo 集中网络异常比对，进行分发处理
        L.e(e.getMessage());
        if (e instanceof HttpException) {
          onException(ExceptionReason.BAD_NETWORK);
        }

    }

    /**
     * 结束标记<成功和失败>，为什么不用onComplete 回调？
     */
    public void onFinish() {
    }

    /**
     * 请求网络失败原因
     */
    public enum ExceptionReason {
        /**
         * 解析数据失败
         */
        PARSE_ERROR,
        /**
         * 网络问题
         */
        BAD_NETWORK,
        /**
         * 连接错误
         */
        CONNECT_ERROR,
        /**
         * 连接超时
         */
        CONNECT_TIMEOUT,
        /**
         * 未知错误
         */
        UNKNOWN_ERROR,
    }

    /**
     * 根据错误类型直接弹出提示
     *
     * @param reason
     */
    private void onException(ExceptionReason reason) {
        switch (reason) {
            case BAD_NETWORK:
                //j进行提示
                break;
            case PARSE_ERROR:
                //j进行提示
                break;
            case CONNECT_ERROR:
                //j进行提示
                break;
            case CONNECT_TIMEOUT:
                //j进行提示
                break;
            case UNKNOWN_ERROR:
                //j进行提示
                break;
        }

    }

}
