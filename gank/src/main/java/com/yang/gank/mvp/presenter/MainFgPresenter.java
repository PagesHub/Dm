package com.yang.gank.mvp.presenter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.yang.gank.R;
import com.yang.gank.mvp.contract.MainFgContract;
import com.yang.gank.ui.home.GkHomeFragment;
import com.yang.gank.ui.info.GkInfoFragment;
import com.yang.gank.ui.news.GkNewsFragment;
import com.yang.gank.ui.video.GkVideoFragment;
import com.yang.sdk.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Describe:   GkMainFragment Presenter
 * Created by Yang on 2019/1/21.
 */
public class MainFgPresenter extends BasePresenter<MainFgContract.View> implements MainFgContract.Presenter {
    private List<Fragment> mFragmentList;

    @Inject
    public MainFgPresenter() {
        super();
        mFragmentList = new ArrayList<>();
    }

    /**
     * 初始化Fragment
     */
    public void initFragment(@NonNull FragmentManager fragmentManager) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //HomeFragment
        GkHomeFragment homeFragment = new GkHomeFragment();
        mFragmentList.add(homeFragment);
        transaction.add(R.id.fragment_container, homeFragment);
        //NewsFragment
        GkNewsFragment newsFragment = new GkNewsFragment();
        mFragmentList.add(newsFragment);
        transaction.add(R.id.fragment_container, newsFragment);
        //VideoFragment
        GkVideoFragment videoFragment = new GkVideoFragment();
        mFragmentList.add(videoFragment);
        transaction.add(R.id.fragment_container, videoFragment);
        //InfoFragment
        GkInfoFragment infoFragment = new GkInfoFragment();
        mFragmentList.add(infoFragment);
        transaction.add(R.id.fragment_container, infoFragment);
        transaction.commitAllowingStateLoss();
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
        fragmentTransaction.commitAllowingStateLoss();
    }
}
