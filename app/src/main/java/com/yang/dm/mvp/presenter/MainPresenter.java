package com.yang.dm.mvp.presenter;


import com.yang.dm.R;
import com.yang.dm.mvp.contract.MainContract;
import com.yang.dm.ui.home.HomeFragment;
import com.yang.dm.ui.info.InfoFragment;
import com.yang.dm.ui.news.NewsFragment;
import com.yang.dm.ui.video.VideoFragment;
import com.yang.sdk.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * Describe:MainActivity_Presenter
 * Created by Yang on 2018/12/19.
 */
public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {
    private List<Fragment> mFragmentList;

    @Inject
    public MainPresenter() {
        super();
        mFragmentList = new ArrayList<>();
    }

    /**
     * 初始化Fragment
     */
    public void initFragment(@NonNull FragmentManager fragmentManager) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //HomeFragment
        HomeFragment homeFragment = new HomeFragment();
        mFragmentList.add(homeFragment);
        transaction.add(R.id.fragment_container, homeFragment);
        //NewsFragment
        NewsFragment newsFragment = new NewsFragment();
        mFragmentList.add(newsFragment);
        transaction.add(R.id.fragment_container, newsFragment);
        //VideoFragment
        VideoFragment videoFragment = new VideoFragment();
        mFragmentList.add(videoFragment);
        transaction.add(R.id.fragment_container, videoFragment);
        //InfoFragment
        InfoFragment infoFragment = new InfoFragment();
        mFragmentList.add(infoFragment);
        transaction.add(R.id.fragment_container, infoFragment);
        transaction.commit();
    }

    /**
     * 根据给定下标选中对应的 fragment
     *
     * @param index fragment 在列表中的下标
     */
    public void selectFragment(@NonNull FragmentManager fragmentManager, int index) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        for (int i = 0; i < mFragmentList.size(); i++) {
            if (i == index) {
                fragmentTransaction.show(mFragmentList.get(i));
            } else {
                fragmentTransaction.hide(mFragmentList.get(i));
            }
        }
        fragmentTransaction.commit();
    }
}
