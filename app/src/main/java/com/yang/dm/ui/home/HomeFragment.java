package com.yang.dm.ui.home;

import android.view.View;

import com.androidkun.xtablayout.XTabLayout;
import com.yang.dm.R;
import com.yang.dm.base.BaseDmFragment;
import com.yang.dm.mvp.contract.HomeContract;
import com.yang.dm.mvp.model.HomeTabAdapterItem;
import com.yang.dm.mvp.presenter.HomePresenter;
import com.yang.dm.ui.adapter.HomePagerAdapter;
import com.yang.dm.widget.DmViewPager;
import com.zhangyue.we.x2c.ano.Xml;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Describe: 主页
 *
 * @author Created by Yang on 2019/1/3.
 */
@Xml(layouts = "fragment_home")
public class HomeFragment extends BaseDmFragment implements HomeContract.View {
    @Inject
    HomePresenter mPresenter;
    @BindView(R.id.home_tab)
    XTabLayout mHomeTab;
    @BindView(R.id.homePager)
    DmViewPager mHomePager;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_home;
    }


    @Override
    protected void initView() {
        fragmentComponent.inject(this);
        mPresenter.attachView(this);
        bindViewPager();
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    /**
     * ViewPager和Tab绑定
     */
    private void bindViewPager() {
        List<HomeTabAdapterItem> homeTabAdapterItems = HomeTabAdapterItem.createHomeFragments(mContext);
        HomePagerAdapter adapter = new HomePagerAdapter(getSupportFragmentManager(), homeTabAdapterItems);
        mHomePager.setAdapter(adapter);
        mHomeTab.setxTabDisplayNum(homeTabAdapterItems.size() >= 5 ? 5 : homeTabAdapterItems.size());
        mHomeTab.setupWithViewPager(mHomePager);
    }

}
