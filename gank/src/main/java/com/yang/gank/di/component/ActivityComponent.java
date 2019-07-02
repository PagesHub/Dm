package com.yang.gank.di.component;


import com.yang.gank.ui.GkMainActivity;
import com.yang.gank.di.module.ActivityModule;
import com.yang.gank.di.scope.ActivityScope;

import dagger.Component;

/**
 * Describe:
 * Created by Yang on 2019/4/19.
 */
@ActivityScope
@Component(modules = ActivityModule.class, dependencies = AppModuleComponent.class)
public interface ActivityComponent {

    void inject(GkMainActivity mainActivity);


}


