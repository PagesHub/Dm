package com.yang.sdk.threadpool.deliver;


import androidx.annotation.NonNull;

import java.util.concurrent.Executor;

/**
  * @Author:         Yang
  * @Description:    默认情况下，用于Java平台的交付
  * @CreateDate:     2019/7/18 2019/7/18
 */
public class JavaDeliver implements Executor {

    private static JavaDeliver instance = new JavaDeliver();

    public static JavaDeliver getInstance() {
        return instance;
    }

    /**
     * 注意增加非空判断
     * @param runnable              runnable
     */
    @Override
    public void execute(@NonNull Runnable runnable) {
        runnable.run();
    }
}

