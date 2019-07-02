package com.yang.sdk.config;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.Utils;
import com.hjq.toast.ToastUtils;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.smtt.sdk.QbSdk;
import com.yang.sdk.BuildConfig;
import com.yang.sdk.callback.BaseLifecycleCallback;
import com.yang.sdk.utils.LogUtils;

/**
 * Describe: 生命周期和application一样
 * Created by Yang on 2019/4/15.
 */
public enum SdkConfig {

    //对象
    INSTANCE;

    private Application mApplication;

    public void initConfig(Application application) {
        this.mApplication = application;
        Utils.init(application);
        ToastUtils.init(application);
        BaseLifecycleCallback.getInstance().init(application);
        LeakCanary.install(application);
        initARouter();
        initX5WebCore();
        initThreadPool();
    }


    /**
     * 初始化线程池管理器
     */
    private void initThreadPool() {
    }

    /**
     * 设置X5初始化完成的回调接口
     */
    private void initX5WebCore() {
        if (!QbSdk.isTbsCoreInited()) {
            QbSdk.preInit(mApplication, new QbSdk.PreInitCallback() {
                @Override
                public void onCoreInitFinished() {
                    LogUtils.eLog("onCoreInitFinished");
                }

                @Override
                public void onViewInitFinished(boolean b) {
                    LogUtils.eLog("onViewInitFinished");
                }
            });
        }
    }

    private void initARouter() {
        if (BuildConfig.DEBUG) { //打印日志
            ARouter.openLog();
            //开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug();
        }
        ARouter.init(mApplication);        //推荐在Application中初始化
    }

}
