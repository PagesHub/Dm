package com.yang.sdk.dagger;

import com.yang.sdk.app.BaseApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Describe:
 * Created by Yang on 2019/4/18.
 */
@Module
public class BaseModule {

    private BaseApplication baseApplication;

    public BaseModule(BaseApplication baseApplication) {
        this.baseApplication = baseApplication;
    }

    @Provides
    @Singleton
    BaseApplication providesApplication() {
        return baseApplication;
    }

}
