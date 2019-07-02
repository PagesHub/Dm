package com.yang.sdk.app;

import android.app.Application;
import android.content.Context;

import com.bumptech.glide.Glide;
import com.yang.sdk.arounter.ARouterUtils;
import com.yang.sdk.arounter.providers.ImplApplication;
import com.yang.sdk.arounter.providers.ModuleConfig;
import com.yang.sdk.config.SdkConfig;
import com.yang.sdk.dagger.BaseComponent;
import com.yang.sdk.dagger.BaseModule;
import com.yang.sdk.dagger.DaggerBaseComponent;

import androidx.multidex.MultiDex;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Describe:
 * Created by Yang on 2019/4/15.
 */
public class BaseApplication extends Application {

    //是否是调试模式
    private static boolean isDebug;
    private static BaseComponent baseComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        //设置调试开关
        setDebug(true);
        SdkConfig.INSTANCE.initConfig(this);
        //在子线程中初始化
        //InitializeService.start(this);
        baseComponent = DaggerBaseComponent.builder().baseModule(new BaseModule(this)).build();

        //Module类的APP初始化
        modulesApplicationInit();
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static BaseComponent getBaseComponent() {
        return baseComponent;
    }

    /**
     * 程序终止的时候执行
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        ARouterUtils.destroy();
    }

    /**
     * 低内存的时候执行
     */
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Glide.get(this).clearMemory();
    }

    /**
     * HOME键退出应用程序
     * 程序在内存清理的时候执行
     */
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level == TRIM_MEMORY_UI_HIDDEN) {
            Glide.get(this).clearMemory();
        }
        Glide.get(this).trimMemory(level);
    }

    /**
     * Module类的APP初始化
     */
    private void modulesApplicationInit() {
        for (String moduleImpl : ModuleConfig.MODULES) {
            try {
                Class<?> clazz = Class.forName(moduleImpl);
                Object obj = clazz.newInstance();
                if (obj instanceof ImplApplication) {
                    ((ImplApplication) obj).onCreate(this);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isDebug() {
        return isDebug;
    }

    public static void setDebug(boolean isDebug) {
        BaseApplication.isDebug = isDebug;
    }
}
