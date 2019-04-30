package com.yang.dm.ui.news;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.yang.dm.R;
import com.yang.dm.app.DmConstants;
import com.yang.dm.base.BaseDmFragment;
import com.yang.dm.mvp.contract.NewsPagerContract;
import com.yang.dm.mvp.model.ReadClassifyChild;
import com.yang.dm.mvp.presenter.NewsPagerPresenter;
import com.yang.dm.ui.adapter.NewsPagerItemAdapter;
import com.zhangyue.we.x2c.ano.Xml;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * Describe:
 *@author  Created by Yang on 2019/1/22.
 */
@Xml(layouts = "fragment_news_pager")
public class NewsPageFragment extends BaseDmFragment implements NewsPagerContract.View {
    @Inject
    NewsPagerPresenter mPresenter;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private NewsPagerItemAdapter mAdapter;
    private List<ReadClassifyChild> mData = new ArrayList<>();
    private String mEnName;


    /**
     * 初始化
     *
     * @param enName 分类
     * @return
     */
    public static NewsPageFragment newInstance(String enName) {
        Bundle args = new Bundle();
        args.putString("enName", enName);
        NewsPageFragment fragment = new NewsPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_news_pager;
    }

    @Override
    protected void initView() {
        fragmentComponent.inject(this);
        mPresenter.attachView(this);
        mEnName = Objects.requireNonNull(getArguments()).getString("enName");
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        mAdapter = new NewsPagerItemAdapter(mData);
        mAdapter.openLoadAnimation();
        mAdapter.setNotDoAnimationCount(DmConstants.PAGE_NUMBER);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("ID", mData.get(position).getId());
            readyGo(ReadActivity.class, bundle);
        });

    }

    @Override
    protected void onFirstUserVisible() {
        super.onFirstUserVisible();
        mPresenter.getReadClassifyChild(mData, mEnName);
    }


    @Override
    public void getReadClassifyChild() {
        mAdapter.setNewData(mData);
        Log.d(getFragmentName(), "getReadClassifyChild: .");
    }

    @Override
    protected View getLoadingTargetView() {
        return getView(R.id.ctl);
    }
}
