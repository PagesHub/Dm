package com.yang.sdk.dagger;

import com.yang.sdk.app.BaseApplication;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Describe:
 * Created by Yang on 2019/4/18.
 */
@Singleton
@Component(modules = {BaseModule.class})
public interface BaseComponent {

    BaseApplication providesApplication();
}
