package com.yang.gank.providers;


import android.content.Context;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yang.gank.ui.GkMainFragment;
import com.yang.sdk.arounter.ARouterConstant;
import com.yang.sdk.arounter.providers.ModuleGankService;


/**
 * Describe: java文件说明
 * Created by Yang on 2019/6/27  17:06
 */
@Route(path = ARouterConstant.GankPath.MODULE_GANK_PROVIDER, name = "ModuleGankServiceImpl")
public class ModuleGankServiceImpl implements ModuleGankService {

    @Override
    public Fragment getModuleGankFragment() {
        return new GkMainFragment();
    }

    @Override
    public void init(Context context) {

    }
}
