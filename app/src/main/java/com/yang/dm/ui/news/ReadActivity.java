package com.yang.dm.ui.news;


import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.yang.dm.R;
import com.yang.dm.base.BaseDmActivity;
import com.yang.dm.mvp.contract.ReadContract;
import com.yang.dm.mvp.model.Read;
import com.yang.dm.mvp.presenter.ReadPresenter;
import com.yang.dm.ui.adapter.ReadItemAdapter;
import com.yang.sdk.loader.GlideUtils;
import com.yang.sdk.web.WebActivity;
import com.yang.sdk.weight.CircleImageView;
import com.zhangyue.we.x2c.ano.Xml;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import io.reactivex.annotations.NonNull;

/**
 * @author
 */
@Xml(layouts = "activity_read")
public class ReadActivity extends BaseDmActivity implements ReadContract.View {
    @Inject
    ReadPresenter mPresenter;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.txv_name)
    TextView mTxvName;
    @BindView(R.id.txv_desc)
    TextView mTxvDesc;
    @BindView(R.id.txv_url)
    TextView mTxvUrl;
    @BindView(R.id.icon)
    CircleImageView mIcon;
    @BindView(R.id.txv_home)
    TextView txvHome;
    @BindView(R.id.collapsingToolbar)
    CollapsingToolbarLayout mCollapsingToolbar;

    private ReadItemAdapter mAdapter;
    private String mId;
    private int page = 1;
    private List<Read> mData = new ArrayList<>();

    @Override
    protected int bindLayout() {
        return R.layout.activity_read;
    }

    @Override
    protected void initView() {
        // 依赖注入
        activityComponent.inject(this);
        mPresenter.attachView(this);
        mCollapsingToolbar.setCollapsedTitleTypeface(Typeface.DEFAULT);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ReadItemAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("Title", mData.get(position).getTitle());
            bundle.putString("WebUrl", mData.get(position).getUrl());
            readyGo(WebActivity.class, bundle);
        });

        mPresenter.getReadInfo(mData, mId, page);
    }

    @Override
    protected void widgetClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.txv_url:
                bundle.putString("Title", mTxvName.getText().toString());
                bundle.putString("WebUrl", mTxvUrl.getText().toString());
                readyGo(WebActivity.class, bundle);
                break;
            default:
                break;
        }
    }

    @Override
    protected void setListener() {
        mTxvUrl.setOnClickListener(this);
    }

    @Override
    public void setReadInfo() {
        mAdapter.setNewData(mData);
        if (mData.get(0).getSite() != null)
            setView(mData.get(0).getSite());
    }

    /**
     * 设置信息
     *
     * @param site
     */
    private void setView(@NonNull Read.SiteBean site) {
        mTxvName.setText(site.getName());
        mTxvDesc.setText(site.getDesc());
        mTxvUrl.setText(site.getUrl());
        GlideUtils.getInstance().glideLoad(this, site.getIcon(), mIcon, 0);
    }


    @Override
    protected View getLoadingTargetView() {
        return getView(R.id.cdl);
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        mId = extras.getString("ID");
    }
}
