package com.yang.dm.app;

import com.yang.dm.di.component.AppModuleComponent;
import com.yang.dm.di.component.DaggerAppModuleComponent;
import com.yang.dm.di.module.AppModule;
import com.yang.sdk.app.BaseApplication;


/**
 * Describe:   Application
 * Created by Yang on 2019/4/16.
 */
public class DmApplication extends BaseApplication {
    private static AppModuleComponent mAppModuleComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppModuleComponent = DaggerAppModuleComponent.builder().baseComponent(getBaseComponent()).appModule(new AppModule(this)).build();

    }
    public static AppModuleComponent getAppModuleComponent() {
        return mAppModuleComponent;
    }
}
