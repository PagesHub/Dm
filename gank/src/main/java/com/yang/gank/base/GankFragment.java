package com.yang.gank.base;

import android.content.Context;

import com.yang.gank.di.component.DaggerFragmentComponent;
import com.yang.gank.di.component.FragmentComponent;
import com.yang.sdk.mvp.BaseFragment;

import org.jetbrains.annotations.NotNull;

/**
 * Describe:  Gank Module基础Fragment
 * Created by Yang on 2019/6/28  10:43
 */
public abstract class GankFragment extends BaseFragment {

    protected FragmentComponent fragmentComponent;

    @Override
    public void onAttach(@NotNull Context context) {
        fragmentComponent = DaggerFragmentComponent.builder().appModuleComponent(GankApplication.getAppModuleComponent()).build();
        super.onAttach(context);
    }
}
