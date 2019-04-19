package com.yang.dm.mvp.model;

import android.content.Context;

import com.yang.dm.R;
import com.yang.dm.ui.home.HomePageFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;

/**
 * Describe:
 * Created by Yang on 2019/1/22.
 */
public class HomeTabAdapterItem {

    private String title;
    private Fragment fragment;

    public String getTitle() {
        return title;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public HomeTabAdapterItem(String title, Fragment fragment) {
        this.title = title;
        this.fragment = fragment;
    }

    /**
     * 创建honePageFragment
     *
     * @param context
     * @return
     */
    public static List<HomeTabAdapterItem> createHomeFragments(Context context) {
        List<HomeTabAdapterItem> adapterItems = new ArrayList<>();
        String[] titleList = new String[]{context.getString(R.string.tab_title_all),
                context.getString(R.string.tab_title_android),
                context.getString(R.string.tab_title_ios),
                context.getString(R.string.tab_title_res),
                context.getString(R.string.tab_title_front_end),
                context.getString(R.string.tab_title_random),
                context.getString(R.string.tab_title_app)};

        for (int i = 0; i < titleList.length; i++) {
            adapterItems.add(new HomeTabAdapterItem(titleList[i], HomePageFragment.newInstance(titleList[i])));
        }
        return adapterItems;
    }
}
