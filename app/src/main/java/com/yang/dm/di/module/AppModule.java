package com.yang.dm.di.module;

import android.app.Application;
import android.content.Context;

import com.yang.dm.app.DmApplication;
import com.yang.dm.app.DmConstants;
import com.yang.dm.di.scope.AppScope;
import com.yang.dm.remote.APIService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Describe:
 * Created by Yang on 2019/4/18.
 */
@Module
public class AppModule {
    private DmApplication application;

    public AppModule(DmApplication application) {
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
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(DmConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @AppScope
    APIService provideAPiService(Retrofit retrofit) {
        return retrofit.create(APIService.class);
    }
}
