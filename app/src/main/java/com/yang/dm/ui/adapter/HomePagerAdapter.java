package com.yang.dm.ui.adapter;

import android.view.ViewGroup;

import com.yang.dm.mvp.model.HomeTabAdapterItem;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Describe:
 * Created by Yang on 2019/1/22.
 */
public class HomePagerAdapter extends FragmentPagerAdapter {
    private List<HomeTabAdapterItem> itemList;

    public HomePagerAdapter(FragmentManager fm, List<HomeTabAdapterItem> itemList) {
        super(fm);
        this.itemList = itemList;
    }

    @NotNull
    @Override
    public Fragment getItem(int position) {
        return itemList.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return itemList.get(position).getTitle();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(@NotNull ViewGroup container, int position, @NotNull Object object) {
        //super.destroyItem(container, position, object);
    }

    @Override
    public float getPageWidth(int position) {
        return super.getPageWidth(position);
    }
}