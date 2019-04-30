package com.yang.dm.ui.video;

import android.view.View;

import com.androidkun.xtablayout.XTabLayout;
import com.yang.dm.R;
import com.yang.dm.base.BaseDmFragment;
import com.yang.dm.mvp.contract.VideoContract;
import com.yang.dm.mvp.model.VideoTab;
import com.yang.dm.mvp.model.VideoTabAdapterItem;
import com.yang.dm.mvp.presenter.VideoPresenter;
import com.yang.dm.ui.adapter.VideoPagerAdapter;
import com.zhangyue.we.x2c.ano.Xml;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * Describe: 视频
 * @author Created by Yang on 2019/1/3.
 */
@Xml(layouts = "fragment_video")
public class VideoFragment extends BaseDmFragment implements VideoContract.View {
    @Inject
    VideoPresenter mPresenter;
    @BindView(R.id.video_tab)
    XTabLayout mVideoTab;
    @BindView(R.id.videoPager)
    ViewPager mVideoPager;

    private List<VideoTab> mData = new ArrayList<>();

    @Override
    protected int bindLayout() {
        return R.layout.fragment_video;
    }

    @Override
    protected void initView() {
        fragmentComponent.inject(this);
        mPresenter.attachView(this);
        mPresenter.getVideoTab(mData);

    }

    /**
     * ViewPager和Tab绑定
     */
    private void bindViewPager() {
        List<VideoTabAdapterItem> videoTabAdapterItems = VideoTabAdapterItem.createHomeFragments(mData);
        VideoPagerAdapter adapter = new VideoPagerAdapter(getSupportFragmentManager(), videoTabAdapterItems);
        mVideoPager.setAdapter(adapter);
        mVideoTab.setxTabDisplayNum(videoTabAdapterItems.size() >= 5 ? 5 : videoTabAdapterItems.size());
        mVideoTab.setupWithViewPager(mVideoPager);
    }


    @Override
    public void setVideoTab() {
        bindViewPager();
    }

    @Override
    protected View getLoadingTargetView() {
        return getView(R.id.ctl);
    }

}
