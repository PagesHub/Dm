package com.yang.dm.app;

import com.didichuxing.doraemonkit.DoraemonKit;
import com.yang.dm.di.component.AppModuleComponent;
import com.yang.dm.di.component.DaggerAppModuleComponent;
import com.yang.dm.di.module.AppModule;
import com.yang.sdk.app.BaseApplication;


/**
 * @author        Yang
 * Description    java类作用描述
 * CreateDate     2019/4/16. 9:46
 */
public class DmApplication extends BaseApplication {
    private static AppModuleComponent mAppModuleComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppModuleComponent = DaggerAppModuleComponent.builder().baseComponent(getBaseComponent()).appModule(new AppModule(this)).build();
        DoraemonKit.install(this);
    }
    public static AppModuleComponent getAppModuleComponent() {
        return mAppModuleComponent;
    }
}
