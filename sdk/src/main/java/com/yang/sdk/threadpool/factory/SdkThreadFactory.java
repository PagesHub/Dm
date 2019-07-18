package com.yang.sdk.threadpool.factory;

import androidx.annotation.NonNull;

import java.util.concurrent.ThreadFactory;


/**
  * @Author:         Yang
  * @Description:    默认Thread工厂
  * @CreateDate:     2019/7/18 2019/7/18
 */
public class SdkThreadFactory implements ThreadFactory {

    private int priority;
    public SdkThreadFactory(int priority) {
        this.priority = priority;
    }


    @Override
    public Thread newThread(@NonNull Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setPriority(priority);
        return thread;
    }

}

