package com.yang.gank.base;

import android.content.Context;

import com.yang.gank.di.component.AppModuleComponent;
import com.yang.gank.di.component.DaggerAppModuleComponent;
import com.yang.gank.di.module.AppModule;
import com.yang.sdk.app.BaseApplication;
import com.yang.sdk.arounter.providers.ImplApplication;

/**
 * Describe: Module作为application时使用
 * Created by Yang on 2019/6/28  9:57
 */
public class GankApplication extends BaseApplication implements ImplApplication {
    private static AppModuleComponent mAppModuleComponent;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public static AppModuleComponent getAppModuleComponent() {
        return mAppModuleComponent;
    }

    @Override
    public void onCreate(BaseApplication application) {
        mAppModuleComponent = DaggerAppModuleComponent.builder().baseComponent(getBaseComponent()).appModule(new AppModule(this)).build();
    }

}
