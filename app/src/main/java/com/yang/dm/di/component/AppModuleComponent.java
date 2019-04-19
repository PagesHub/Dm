package com.yang.dm.di.component;

import com.yang.dm.di.module.AppModule;
import com.yang.dm.di.scope.AppScope;
import com.yang.dm.remote.DataManager;
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
