package com.yang.dm.ui.home;

import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yang.dm.R;
import com.yang.dm.app.DmConstants;
import com.yang.dm.base.BaseDmFragment;
import com.yang.dm.mvp.contract.HomePagerContract;
import com.yang.dm.mvp.model.GanHuo;
import com.yang.dm.mvp.presenter.HomePagerPresenter;
import com.yang.dm.ui.adapter.HomePagerItemAdapter;
import com.yang.sdk.constant.Constant;
import com.yang.sdk.web.WebActivity;
import com.zhangyue.we.x2c.ano.Xml;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * Describe:
 *
 * @author Created by Yang on 2019/1/22.
 */
@Xml(layouts = "fragment_home_pager")
public class HomePageFragment extends BaseDmFragment implements HomePagerContract.View {
    @Inject
    HomePagerPresenter mPresenter;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private HomePagerItemAdapter mAdapter;
    private String mCategory;
    private int page = 1;

    /**
     * 初始化
     *
     * @param tabTitle 标题
     * @return
     */
    public static HomePageFragment newInstance(String tabTitle) {
        Bundle args = new Bundle();
        args.putString("Title", tabTitle);
        HomePageFragment fragment = new HomePageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_home_pager;
    }

    @Override
    protected void initView() {
        fragmentComponent.inject(this);
        mPresenter.attachView(this);
        mCategory = Objects.requireNonNull(getArguments()).getString("Title");
        mCategory = getString(R.string.tab_title_all).equalsIgnoreCase(mCategory) ? "all" : mCategory;
        //设置RecyclerView
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new HomePagerItemAdapter();
        mAdapter.openLoadAnimation();
        mAdapter.setNotDoAnimationCount(DmConstants.PAGE_NUMBER);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString(Constant.WEB_URL, mAdapter.getData().get(position).getUrl());
            bundle.putString(Constant.WEB_TITLE, mAdapter.getData().get(position).getDesc());
            readyGo(WebActivity.class, bundle);
        });
        mAdapter.setOnLoadMoreListener(() -> {
            mPresenter.getGanHuo(mCategory, page);
        }, mRecyclerView);
    }

    @Override
    protected void onFirstUserVisible() {
        super.onFirstUserVisible();
        mPresenter.getGanHuo(mCategory, page);
    }

    @Override
    protected void onUserInvisible() {
        super.onUserInvisible();
    }

    @Override
    public void setGanHuo(List<GanHuo> data) {
        if (data.size() == 0) {
            mAdapter.loadMoreEnd();
            return;
        }
        mAdapter.addData(data);
        mAdapter.loadMoreComplete();
        page++;
    }

    @Override
    protected View getLoadingTargetView() {
        return getView(R.id.ctl);
    }
}
