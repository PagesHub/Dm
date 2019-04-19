package com.yang.dm.base;

import android.os.Bundle;

import com.yang.dm.app.DmApplication;
import com.yang.dm.di.component.ActivityComponent;
import com.yang.dm.di.component.DaggerActivityComponent;
import com.yang.sdk.mvp.BaseActivity;

import androidx.annotation.Nullable;

/**
 * Describe:
 * Created by Yang on 2019/4/17.
 */
public abstract class DmBaseActivity extends BaseActivity {
    protected ActivityComponent activityComponent;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        activityComponent = DaggerActivityComponent.builder().appModuleComponent(DmApplication.getAppModuleComponent()).build();
        super.onCreate(savedInstanceState);
    }

}
