package com.yang.gank.ui.adpater;

import android.view.View;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yang.gank.R;
import com.yang.gank.mvp.model.GanHuo;
import com.yang.gank.utils.BannerHelper;
import com.yang.sdk.manager.FontManager;
import com.yang.sdk.utils.DateUtils;
import com.youth.banner.Banner;

/**
 * Describe: HomePager RecyclerView adapter
 * Created by Yang on 2019/7/2  12:01
 */
public class HomePagerItemAdapter extends BaseQuickAdapter<GanHuo, BaseViewHolder> {
    private Banner mBanner;

    public HomePagerItemAdapter() {
        super(R.layout.fragment_gk_home_pager_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, GanHuo item) {
        helper.setText(R.id.txv_title, item.getDesc());
        helper.setText(R.id.txv_name, item.getWho());
        FontManager.viewFonts(mContext, helper.getView(R.id.txv_name), "SnellRoundHand.ttf");
        helper.setText(R.id.txv_time, DateUtils.formatTime(DateUtils.dealDateFormat(item.getCreatedAt()), "yyyy/MM/dd HH:SS:mm"));
        mBanner = helper.getView(R.id.banner);
        if (item.getImages() != null && item.getImages().size() != 0)
            BannerHelper.initBanner(mBanner,item.getImages(), null,true);
        else
            mBanner.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onViewAttachedToWindow(BaseViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        mBanner.startAutoPlay();    //开始轮播
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull BaseViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        mBanner.stopAutoPlay();    //结束轮播
    }
}
