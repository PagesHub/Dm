package com.yang.gank.base;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.yang.gank.di.component.ActivityComponent;
import com.yang.gank.di.component.DaggerActivityComponent;
import com.yang.sdk.mvp.BaseActivity;

/**
  * @author        Yang
  * Description    Gank Module基础activity
  * CreateDate     2019/4/26 11:30
 */
public abstract class GankActivity extends BaseActivity {
    protected ActivityComponent activityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        activityComponent = DaggerActivityComponent.builder().appModuleComponent(GankApplication.getAppModuleComponent()).build();
        super.onCreate(savedInstanceState);
    }

}
