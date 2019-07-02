package com.yang.gank.di.component;

import com.yang.gank.di.module.AppModule;
import com.yang.gank.di.scope.AppScope;
import com.yang.gank.remote.DataManager;
import com.yang.sdk.dagger.BaseComponent;

import dagger.Component;

/**
 * Describe:
 * Created by Yang on 2019/4/18.
 */
@AppScope
@Component(dependencies = BaseComponent.class, modules = {AppModule.class})
public interface AppModuleComponent {
    
    DataManager getDataManager();
}
