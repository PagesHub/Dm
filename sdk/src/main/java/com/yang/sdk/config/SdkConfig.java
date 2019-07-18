package com.yang.sdk.config;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.Utils;
import com.hjq.toast.ToastUtils;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.smtt.sdk.QbSdk;
import com.yang.sdk.BuildConfig;
import com.yang.sdk.callback.BaseLifecycleCallback;
import com.yang.sdk.callback.LogCallback;
import com.yang.sdk.threadpool.PoolThread;
import com.yang.sdk.utils.LogUtils;

/**
 * Describe: 生命周期和application一样
 * Created by Yang on 2019/4/15.
 */
public enum SdkConfig {

    //对象
    INSTANCE;

    private Application mApplication;
    private PoolThread executor;


    public void initConfig(Application application) {
        this.mApplication = application;
        initARouter();
        initThreadPool();
        Utils.init(application);
        ToastUtils.init(application);
        BaseLifecycleCallback.getInstance().init(application);
        LeakCanary.install(application);
    }


    /**
     * 初始化线程池管理器
     */
    private void initThreadPool() {
        // 创建一个独立的实例进行使用
        if (executor==null){
            executor = PoolThread.ThreadBuilder
                    .createFixed(6)
                    .setPriority(Thread.MAX_PRIORITY)
                    .setCallback(new LogCallback())
                    .build();
        }
    }

    public PoolThread getExecutor() {
        return executor;
    }

    private void initARouter() {
        if (BuildConfig.DEBUG) {
            //打印日志
            ARouter.openLog();
            //开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug();
        }
        //推荐在Application中初始化
        ARouter.init(mApplication);
    }

}
