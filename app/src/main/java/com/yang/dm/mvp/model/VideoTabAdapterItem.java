package com.yang.dm.mvp.model;


import com.yang.dm.ui.video.VideoPageFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import io.reactivex.annotations.Nullable;

/**
 * Describe:
 * Created by Yang on 2019/1/22.
 */
public class VideoTabAdapterItem {
    private String title;
    private Fragment fragment;

    public String getTitle() {
        return title;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public VideoTabAdapterItem(String title, Fragment fragment) {
        this.title = title;
        this.fragment = fragment;
    }

    /**
     * 创建honePageFragment
     *
     * @param date
     * @return
     */
    public static List<VideoTabAdapterItem> createHomeFragments(@Nullable List<VideoTab> date) {
        List<VideoTabAdapterItem> adapterItems = new ArrayList<>();
        for (VideoTab videoTab : date) {
            adapterItems.add(new VideoTabAdapterItem(videoTab.getName(), VideoPageFragment.newInstance(videoTab.getApiUrl(),videoTab.getId())));
        }
        return adapterItems;
    }
}
