package com.yang.sdk.threadpool.config;

import com.yang.sdk.threadpool.callback.AsyncCallback;
import com.yang.sdk.threadpool.callback.ThreadCallback;

import java.util.concurrent.Executor;

/**
  * @Author:         Yang
  * @Description:    存储当前任务的某些配置
  * @CreateDate:     2019/7/18 2019/7/18
 */
public class ThreadConfigs {

    /**
     * 线程的名称
     * 通过setName方法设置
     */
    public String name;
    /**
     * 线程执行延迟的时间
     * 通过setDelay方法设置
     */
    public long delay;
    /**
     * 线程执行者
     * JAVA或者ANDROID
     */
    public Executor deliver;
    /**
     * 用户任务的状态回调callback
     */
    public ThreadCallback callback;
    /**
     * 异步callback回调callback
     */
    public AsyncCallback asyncCallback;
}
