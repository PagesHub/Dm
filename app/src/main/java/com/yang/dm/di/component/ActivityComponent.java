package com.yang.dm.di.component;

import com.yang.dm.di.module.ActivityModule;
import com.yang.dm.di.scope.ActivityScope;
import com.yang.dm.ui.MainActivity;
import com.yang.dm.ui.news.ReadActivity;

import dagger.Component;

/**
 * Describe:
 * Created by Yang on 2019/4/19.
 */
@ActivityScope
@Component(modules = ActivityModule.class, dependencies = AppModuleComponent.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(ReadActivity h5Activity);
}


