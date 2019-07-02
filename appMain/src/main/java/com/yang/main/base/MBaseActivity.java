package com.yang.main.base;



import android.os.Bundle;

import androidx.annotation.Nullable;

import com.yang.sdk.mvp.BaseActivity;

import butterknife.ButterKnife;

/**
 * Describe: Main Module BaseActivity
 * Created by Yang on 2019/6/20  17:50
 */
public  abstract class MBaseActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
