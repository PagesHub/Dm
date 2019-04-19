package com.yang.dm.ui.news;

import android.view.View;

import com.androidkun.xtablayout.XTabLayout;
import com.yang.dm.R;
import com.yang.dm.base.DmBaseFragment;
import com.yang.dm.mvp.contract.NewsContract;
import com.yang.dm.mvp.model.NewsTabAdapterItem;
import com.yang.dm.mvp.model.ReadClassify;
import com.yang.dm.mvp.presenter.NewsPresenter;
import com.yang.dm.ui.adapter.NewsPagerAdapter;
import com.zhangyue.we.x2c.ano.Xml;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * Describe: 杂闻
 * Created by Yang on 2019/1/3.
 */
@Xml(layouts = "fragment_news")
public class NewsFragment extends DmBaseFragment implements NewsContract.View {

    @Inject
    NewsPresenter mPresenter;
    @BindView(R.id.news_tab)
    XTabLayout mNewsTab;
    @BindView(R.id.newsPager)
    ViewPager mNewsPager;
    /**
     * 闲读分类
     */
    private List<ReadClassify> mData = new ArrayList<>();

    @Override
    protected int bindLayout() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initView() {
        fragmentComponent.inject(this);
        mPresenter.attachView(this);
        mPresenter.getReadClassify(mData);
    }

    @Override
    public void setReadClassify() {
        bindViewPager();
    }

    /**
     * ViewPager和Tab绑定
     */
    private void bindViewPager() {
        List<NewsTabAdapterItem> newsTabAdapterItems = NewsTabAdapterItem.createHomeFragments(mData);
        NewsPagerAdapter adapter = new NewsPagerAdapter(getSupportFragmentManager(), newsTabAdapterItems);
        mNewsPager.setAdapter(adapter);
        mNewsTab.setxTabDisplayNum(newsTabAdapterItems.size()>=4?4:newsTabAdapterItems.size());
        mNewsTab.setupWithViewPager(mNewsPager);
    }

    @Override
    protected View getLoadingTargetView() {
        return getView(R.id.ctl);
    }

}
