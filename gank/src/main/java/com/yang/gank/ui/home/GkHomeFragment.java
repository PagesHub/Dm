package com.yang.gank.ui.home;



import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.androidkun.xtablayout.XTabLayout;
import com.yang.gank.R;
import com.yang.gank.R2;
import com.yang.gank.base.GankFragment;
import com.yang.gank.mvp.contract.HomeContract;
import com.yang.gank.mvp.presenter.HomePresenter;
import com.zhangyue.we.x2c.ano.Xml;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Describe: 主页
 * Created by Yang on 2019/7/1  14:34
 */
@Xml(layouts = "fragment_gk_home")
public class GkHomeFragment extends GankFragment implements HomeContract.View {
    @Inject
    HomePresenter mPresenter;
    @BindView(R2.id.home_tab)
    XTabLayout mHomeTab;
    @BindView(R2.id.homePager)
    ViewPager2 mHomePager;
    private List<Fragment> mFragments = new ArrayList<>();

    @Override
    protected int bindLayout() {
        return R.layout.fragment_gk_home;
    }

    @Override
    protected void initView() {
        fragmentComponent.inject(this);
        mPresenter.attachView(this);
        String[] titles = mContext.getResources().getStringArray(R.array.home_tabs);
        for (String s : titles) {
            mHomeTab.addTab(mHomeTab.newTab().setText(s));
            mFragments.add(GkHomePageFragment.newInstance(s));
        }
        mHomeTab.addOnTabSelectedListener(new XTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(XTabLayout.Tab tab) {
                mHomePager.setCurrentItem(tab.getPosition(), true);
            }

            @Override
            public void onTabUnselected(XTabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(XTabLayout.Tab tab) {

            }
        });
        mHomePager.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getItemCount() {
                return mFragments.size();
            }
        });
        mHomePager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                mHomeTab.setScrollPosition(position, positionOffset, false);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Objects.requireNonNull(mHomeTab.getTabAt(position)).select();
            }
        });
        mHomePager.setOffscreenPageLimit(mFragments.size());
    }
}
