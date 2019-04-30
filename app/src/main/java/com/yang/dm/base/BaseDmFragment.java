package com.yang.dm.base;

import android.content.Context;

import com.yang.dm.app.DmApplication;
import com.yang.dm.di.component.DaggerFragmentComponent;
import com.yang.dm.di.component.FragmentComponent;
import com.yang.sdk.mvp.BaseFragment;

import org.jetbrains.annotations.NotNull;

/**
  * @author        Yang
  * Description    java类作用描述
  * CreateDate     2019/4/26 11:31
 */
public abstract class BaseDmFragment extends BaseFragment {
    protected FragmentComponent fragmentComponent;

    @Override
    public void onAttach(@NotNull Context context) {
        fragmentComponent = DaggerFragmentComponent.builder().appModuleComponent(DmApplication.getAppModuleComponent()).build();
        super.onAttach(context);
    }

}
