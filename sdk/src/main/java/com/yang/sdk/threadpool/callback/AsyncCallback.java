package com.yang.sdk.threadpool.callback;


/**
  * @Author:         Yang
  * @Description:    异步callback回调接口
  * @CreateDate:     2019/7/18 2019/7/18
 */
public interface AsyncCallback<T> {

    /**
     * 成功时调用
     *
     * @param t 泛型
     */
    void onSuccess(T t);

    /**
     * 异常时调用
     *
     * @param t 异常
     */
    void onFailed(Throwable t);


    /**
     * 通知用户任务开始运行
     *
     * @param threadName 正在运行线程的名字
     */
    void onStart(String threadName);
}
