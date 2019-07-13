package com.yang.gank.ui;


import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.yang.gank.R;
import com.yang.gank.R2;
import com.yang.gank.base.GankFragment;
import com.yang.gank.mvp.contract.MainFgContract;
import com.yang.gank.mvp.presenter.MainFgPresenter;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Describe: 干货集中module主页面,工程下进行单Activity+Fragments
 * Created by Yang on 2019/6/27  17:14
 */
public class GkMainFragment extends GankFragment implements MainFgContract.View {
    @Inject
    MainFgPresenter mPresenter;
    @BindView(R2.id.bottom_nav)
    BottomNavigationViewEx mBottomNv;

    private FragmentManager mFragmentManager;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_gk_main;
    }

    @Override
    protected void initView() {
        // 依赖注入
        fragmentComponent.inject(this);
        mPresenter.attachView(this);
        //Fragment管理器
        mFragmentManager = getChildFragmentManager();
        //创建 fragment
        mPresenter.initFragment(mFragmentManager);
        mPresenter.selectFragment(mFragmentManager, 0);
        //设置底部
        setupBottomNavigationView();
    }

    /**
     * 设置底部标题
     */
    private void setupBottomNavigationView() {
        mBottomNv.enableAnimation(false);
        mBottomNv.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        mBottomNv.setItemHorizontalTranslationEnabled(false);
        mBottomNv.setOnNavigationItemSelectedListener(item -> {
            int i = item.getItemId();
            if (i == R.id.home) {
                mPresenter.selectFragment(mFragmentManager, 0);
            } else if (i == R.id.news) {
                mPresenter.selectFragment(mFragmentManager, 1);
            } else if (i == R.id.video) {
                mPresenter.selectFragment(mFragmentManager, 2);
            } else if (i == R.id.info) {
                mPresenter.selectFragment(mFragmentManager, 3);
            }
            return true;
        });
    }
}
