package com.yang.gank.di.component;




import com.yang.gank.di.module.FragmentModule;
import com.yang.gank.di.scope.FragmentScope;
import com.yang.gank.ui.GkMainFragment;
import com.yang.gank.ui.home.GkHomeFragment;
import com.yang.gank.ui.home.GkHomePageFragment;

import dagger.Component;

/**
 * Describe:
 * Created by Yang on 2018/12/18.
 */
@FragmentScope
@Component(modules = FragmentModule.class, dependencies = AppModuleComponent.class)
public interface FragmentComponent {
    void inject(GkMainFragment gkMainFragment);

    void inject(GkHomeFragment homeFragment);

    void inject(GkHomePageFragment homePageFragment);
}
