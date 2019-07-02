package com.yang.gank.di.module;

import android.app.Application;
import android.content.Context;


import com.yang.gank.base.GankConstants;
import com.yang.gank.di.scope.AppScope;
import com.yang.gank.remote.APIService;
import com.yang.sdk.http.retrofit.RetrofitWrapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
/**
 * Describe:
 * Created by Yang on 2019/4/18.
 */
@Module
public class AppModule {
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return application;
    }

    @Provides
    @AppScope
    APIService provideAPiService() {
        return RetrofitWrapper.getInstance(GankConstants.BASE_URL).create(APIService.class);
    }
}
