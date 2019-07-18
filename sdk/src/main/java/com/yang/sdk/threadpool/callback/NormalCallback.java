package com.yang.sdk.threadpool.callback;

import java.util.concurrent.Executor;

/**
  * @Author:         Yang
  * @Description:    回调委托类，监听
  * @CreateDate:     2019/7/18 2019/7/18
 */
public final class NormalCallback implements ThreadCallback, AsyncCallback {

    private ThreadCallback callback;
    private AsyncCallback async;
    private Executor deliver;

    public NormalCallback(ThreadCallback callback, Executor deliver, AsyncCallback async) {
        this.callback = callback;
        this.deliver = deliver;
        this.async = async;
    }

    /**
     * 回调成功
     * @param o
     */
    @Override
    public void onSuccess(final Object o) {
        if (async == null) {
            return;
        }
        deliver.execute(() -> {
            try {
                //noinspection unchecked
                async.onSuccess(o);
            } catch (Throwable t) {
                onFailed(t);
            }
        });
    }

    /**
     * 回调失败
     * @param t         异常
     */
    @Override
    public void onFailed(final Throwable t) {
        if (async == null) {
            return;
        }
        deliver.execute(() -> async.onFailed(t));
    }

    /**
     * 回调异常
     * @param name                  线程name
     * @param t                     异常
     */
    @Override
    public void onError(final String name, final Throwable t) {
        onFailed(t);
        if (callback == null) {
            return;
        }
        deliver.execute(() -> callback.onError(name, t));
    }

    /**
     * 回调完成
     * @param name                  线程name
     */
    @Override
    public void onCompleted(final String name) {
        if (callback == null) {
            return;
        }
        deliver.execute(() -> callback.onCompleted(name));
    }

    /**
     * 回调开始
     * @param name                  线程name
     */
    @Override
    public void onStart(final String name) {
        if (callback == null) {
            return;
        }
        deliver.execute(() -> callback.onStart(name));
    }
}

