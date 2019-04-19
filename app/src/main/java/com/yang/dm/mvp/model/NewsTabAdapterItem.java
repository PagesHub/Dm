package com.yang.dm.mvp.model;

import com.yang.dm.ui.news.NewsPageFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import io.reactivex.annotations.Nullable;

/**
 * Describe:
 * Created by Yang on 2019/1/22.
 */
public class NewsTabAdapterItem {

    private String title;
    private Fragment fragment;

    public String getTitle() {
        return title;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public NewsTabAdapterItem(String title, Fragment fragment) {
        this.title = title;
        this.fragment = fragment;
    }

    /**
     * 创建honePageFragment
     *
     * @param date
     * @return
     */
    public static List<NewsTabAdapterItem> createHomeFragments(@Nullable List<ReadClassify> date) {
        List<NewsTabAdapterItem> adapterItems = new ArrayList<>();
        for (ReadClassify readClassify : date) {
            adapterItems.add(new NewsTabAdapterItem(readClassify.getName(), NewsPageFragment.newInstance(readClassify.getEn_name())));
        }
        return adapterItems;
    }
}
