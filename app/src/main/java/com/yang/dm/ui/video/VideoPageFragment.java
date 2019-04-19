package com.yang.dm.ui.video;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;

import com.yang.dm.R;
import com.yang.dm.app.DmConstants;
import com.yang.dm.base.DmBaseFragment;
import com.yang.dm.mvp.contract.VideoPagerContract;
import com.yang.dm.mvp.model.ContentBean;
import com.yang.dm.mvp.model.VideoDetails;
import com.yang.dm.mvp.model.VideoDiscovery;
import com.yang.dm.mvp.model.VideoRecommend;
import com.yang.dm.mvp.model.VideoResponse;
import com.yang.dm.mvp.presenter.VideoPagerPresenter;
import com.yang.dm.ui.adapter.VideoDetailsItemAdapter;
import com.yang.dm.ui.adapter.VideoDisClassifyAdapter;
import com.yang.dm.ui.adapter.VideoDisTopAdapter;
import com.youth.banner.Banner;
import com.zhangyue.we.x2c.ano.Xml;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * Describe:
 * Created by Yang on 2019/1/22.
 */
@Xml(layouts = "fragment_video_pager")
public class VideoPageFragment extends DmBaseFragment implements VideoPagerContract.View {
    @Inject
    VideoPagerPresenter mPresenter;
    @BindView(R.id.viewStub)
    ViewStub mViewStub;
    private Banner mBannerTop, mBannerCenter;
    private VideoDetailsItemAdapter mAdapter;
    private List<VideoDetails> mDeData = new ArrayList<>();
    private List<VideoDiscovery> mDsData = new ArrayList<>();
    private String mApiUrl;
    private int mID;


    /**
     * 初始化
     *
     * @param apiUrl 分类
     * @return
     */
    public static VideoPageFragment newInstance(String apiUrl, int id) {
        Bundle args = new Bundle();
        args.putString("apiUrl", apiUrl);
        args.putInt("id", id);
        VideoPageFragment fragment = new VideoPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mBannerTop != null)
            mBannerTop.startAutoPlay();
        if (mBannerCenter != null)
            mBannerCenter.startAutoPlay();
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_video_pager;
    }

    @Override
    protected void initView() {
        fragmentComponent .inject(this);
        mPresenter.attachView(this);
        mApiUrl = Objects.requireNonNull(getArguments()).getString("apiUrl");
        mID = Objects.requireNonNull(getArguments()).getInt("id");
        if (mID >= 0) {
            initListView();
        } else {
            initDetailsView();
        }
    }

    @Override
    protected void onFirstUserVisible() {
        super.onFirstUserVisible();
    }

    /**
     * 设置列表界面
     */
    private void initListView() {
        RecyclerView recyclerView=getView(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new VideoDetailsItemAdapter();
        mAdapter.openLoadAnimation();
        mAdapter.setNotDoAnimationCount(DmConstants.PAGE_NUMBER);
        recyclerView.setAdapter(mAdapter);
        mPresenter.getVideos(mDeData, DmConstants.VIDEO_CATEGORY_URL + mID);
    }

    /**
     * 设置详情界面
     */
    private void initDetailsView() {
        mViewStub.inflate();
        //FrameLayout 加载不同布局
        FrameLayout frameLayout = getView(R.id.frameLayout);
        int layoutRes = R.layout.videos_discover_layout;
        switch (mID) {
            case -1:
                layoutRes = R.layout.videos_discover_layout;
                mPresenter.getDsVideos(mDsData, mApiUrl);
                break;
            case -2:
                layoutRes = R.layout.videos_recommend_layout;
                mPresenter.getRecVideos(mApiUrl);
                break;
            case -3:
                break;
            case -4:
                break;
        }
        frameLayout.addView(getLayoutInflater().inflate(layoutRes, null));
    }

    @Override
    public void setVideos() {
        mAdapter.setNewData(mDeData);
    }

    @Override
    public void setDsVideos() {
        mBannerTop = getView(R.id.banner_top);
        mBannerCenter = getView(R.id.banner_center);
        List<ContentBean.DataBean> classifyData = new ArrayList<>();
        for (int i = 0; i < mDsData.size(); i++) {
            VideoDiscovery discovery = mDsData.get(i);
            if (i == 0)
                mPresenter.setTopBanner(mBannerTop, discovery);
            if (i == 11)
                mPresenter.setTopBanner(mBannerCenter, discovery);
            if (TextUtils.equals("briefCard", discovery.getType()))
                classifyData.add(discovery.getData());
        }
        setText(R.id.txv_hot, mDsData.get(1).getData().getText());
        setText(R.id.txv_all_hot, mDsData.get(5).getData().getText());
        setText(R.id.txv_top, mDsData.get(6).getData().getText());
        setText(R.id.txv_all_top, mDsData.get(10).getData().getText());
        setText(R.id.txv_special, mDsData.get(11).getData().getHeader().getTitle());
        setText(R.id.txv_author, mDsData.get(12).getData().getText());
         setClassifyList(classifyData);
         setTopList();
         setHotAuthorList();
    }

    /**
     * 设置热门
     *
     * @param date 数据中
     */
    private void setClassifyList(List<ContentBean.DataBean> date) {
        RecyclerView recyclerViewClassify = getView(R.id.recyclerView_classify);
        recyclerViewClassify.setLayoutManager(new LinearLayoutManager(mContext));
        VideoDisClassifyAdapter classifyAdapter = new VideoDisClassifyAdapter(date);
        recyclerViewClassify.setAdapter(classifyAdapter);
        recyclerViewClassify.setNestedScrollingEnabled(false);//禁止滑动
    }

    /**
     * 本周排行
     */
    private void setTopList() {
        RecyclerView recyclerViewTop = getView(R.id.recyclerView_top);
        recyclerViewTop.setLayoutManager(new LinearLayoutManager(mContext));
        VideoDisTopAdapter topAdapter = new VideoDisTopAdapter();
        recyclerViewTop.setAdapter(topAdapter);
        topAdapter.addData(topAdapter.getExpandableData(mDsData));
        topAdapter.expandAll();
        recyclerViewTop.setNestedScrollingEnabled(false);//禁止滑动
    }

    /**
     * 热门作者
     */
    private void setHotAuthorList() {
        RecyclerView recyclerViewHotAuthor = getView(R.id.recyclerView_hot_author);
        recyclerViewHotAuthor.setLayoutManager(new LinearLayoutManager(mContext));
        VideoDisTopAdapter topAdapter = new VideoDisTopAdapter();
        recyclerViewHotAuthor.setAdapter(topAdapter);
        topAdapter.addData(topAdapter.getHotAuthorData(mDsData));
        topAdapter.expandAll();
        recyclerViewHotAuthor.setNestedScrollingEnabled(false);//禁止滑动
    }

    @Override
    public void setRecVideos(VideoResponse<VideoRecommend> recVideos) {
//        VideoRecommend recommend = recVideos.getItemList().get(0);
//        setText(R.id.txv_rec_title, recommend.getData().getHeader().getTitle());
//        setText(R.id.txv_rec_tips, recommend.getData().getHeader().getRightText());
//        RecyclerView recyclerView = getView(R.id.recyclerView_rec);
//        List<VideoRecommend> itemList = recVideos.getItemList();

    }


    @Override
    protected View getLoadingTargetView() {
        return getView(R.id.ctl);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mBannerTop != null)
            mBannerTop.stopAutoPlay();
        if (mBannerCenter != null)
            mBannerCenter.stopAutoPlay();
    }
}
