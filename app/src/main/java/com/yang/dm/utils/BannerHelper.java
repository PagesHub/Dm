package com.yang.dm.utils;

import com.yang.sdk.loader.BannerImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Describe:Banner帮助
 * Created by Yang on 2019/2/15.
 */
public class BannerHelper {
    /**
     * 初始化Banner
     */
    public static void initBanner(@NonNull Banner banner, @NonNull List<String> imgRes, @Nullable List<String> titleRes, boolean autoPlay) {
        //设置banner样式
        //banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new BannerImageLoader());
        //设置图片集合
        banner.setImages(imgRes);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        if (titleRes != null && titleRes.size() != 0)
            banner.setBannerTitles(titleRes);
        //设置自动轮播，默认为true
        banner.isAutoPlay(autoPlay);
        //设置轮播时间
        banner.setDelayTime(5000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }
}
