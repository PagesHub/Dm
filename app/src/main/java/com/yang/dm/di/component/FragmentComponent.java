package com.yang.dm.di.component;


import com.yang.dm.di.module.FragmentModule;
import com.yang.dm.di.scope.FragmentScope;
import com.yang.dm.ui.home.HomeFragment;
import com.yang.dm.ui.home.HomePageFragment;
import com.yang.dm.ui.info.InfoFragment;
import com.yang.dm.ui.news.NewsFragment;
import com.yang.dm.ui.news.NewsPageFragment;
import com.yang.dm.ui.video.VideoFragment;
import com.yang.dm.ui.video.VideoPageFragment;

import dagger.Component;

/**
 * Describe:
 * Created by Yang on 2018/12/18.
 */
@FragmentScope
@Component(modules = FragmentModule.class, dependencies = AppModuleComponent.class)
public interface FragmentComponent {

    void inject(HomeFragment homeFragment);

    void inject(HomePageFragment homePageFragment);

    void inject(NewsFragment newsFragment);

    void inject(NewsPageFragment newsPageFragment);

    void inject(VideoFragment videoFragment);

    void inject(VideoPageFragment videoPageFragment);

    void inject(InfoFragment infoFragment);
}
