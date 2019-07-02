package com.yang.gank.ui.home;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ethanhua.skeleton.RecyclerViewSkeletonScreen;
import com.ethanhua.skeleton.Skeleton;
import com.yang.gank.R;
import com.yang.gank.R2;
import com.yang.gank.base.GankFragment;
import com.yang.gank.mvp.contract.HomePagerContract;
import com.yang.gank.mvp.model.GanHuo;
import com.yang.gank.mvp.presenter.HomePagerPresenter;
import com.yang.gank.ui.adpater.HomePagerItemAdapter;
import com.yang.sdk.constant.Constants;
import com.yang.sdk.weight.DmRecyclerView;
import com.zhangyue.we.x2c.ano.Xml;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;


@Xml(layouts = "fragment_gk_home_pager")
public class GkHomePageFragment extends GankFragment implements HomePagerContract.View {
    @Inject
    HomePagerPresenter mPresenter;
    @BindView(R2.id.hp_rcy)
    DmRecyclerView mHPRcy;
    private HomePagerItemAdapter mAdapter;
    private String mCategory;
    private int page = 1;
    private RecyclerViewSkeletonScreen skeletonScreen;

    /**
     * 初始化
     *
     * @param tabTitle 标题
     * @return
     */
    public static GkHomePageFragment newInstance(String tabTitle) {
        Bundle args = new Bundle();
        args.putString("Title", tabTitle);
        GkHomePageFragment fragment = new GkHomePageFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int bindLayout() {
        return R.layout.fragment_gk_home_pager;
    }

    @Override
    protected void initView() {
        fragmentComponent.inject(this);
        mPresenter.attachView(this);

        mCategory = Objects.requireNonNull(getArguments()).getString("Title");
        if ("全部".equalsIgnoreCase(mCategory))
            mCategory = "all";
        if ("IOS".equalsIgnoreCase(mCategory))
            mCategory = "iOS";
        initRcy();
        mPresenter.getGanHuo(mCategory, page);
    }

    /**
     * 设置RecyclerView
     */
    private void initRcy() {
        mHPRcy.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new HomePagerItemAdapter();
        mAdapter.openLoadAnimation();
        mAdapter.setNotDoAnimationCount(Constants.PAGE_NUMBER);
        mHPRcy.setAdapter(mAdapter);
        mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        mAdapter.setOnLoadMoreListener(() -> mPresenter.getGanHuo(mCategory, page), mHPRcy);

        skeletonScreen = Skeleton.bind(mHPRcy)
                .adapter(mAdapter)
                .load(R.layout.fragment_gk_home_pager_skeleton)
                .show();
    }


    @Override
    public void setGanHuo(List<GanHuo> data) {
        if (data.size() == 0) {
            mAdapter.loadMoreEnd();
            return;
        }
        skeletonScreen.hide();
        mAdapter.addData(data);
        mAdapter.loadMoreComplete();
        page++;
    }

    @Override
    protected View getLoadingTargetView() {
        return getView(R.id.ctl);
    }
}
