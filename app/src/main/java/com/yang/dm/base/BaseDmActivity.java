package com.yang.dm.base;

import android.os.Bundle;

import com.yang.dm.app.DmApplication;
import com.yang.dm.di.component.ActivityComponent;
import com.yang.dm.di.component.DaggerActivityComponent;
import com.yang.sdk.mvp.BaseActivity;

import androidx.annotation.Nullable;

/**
  * @author        Yang
  * Description    java类作用描述
  * CreateDate     2019/4/26 11:30
 */
public abstract class BaseDmActivity extends BaseActivity {
    protected ActivityComponent activityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        activityComponent = DaggerActivityComponent.builder().appModuleComponent(DmApplication.getAppModuleComponent()).build();
        super.onCreate(savedInstanceState);
    }

}
