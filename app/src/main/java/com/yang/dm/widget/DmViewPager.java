package com.yang.dm.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * Describe:项目ViewPager
 * Created by Yang on 2019/1/22.
 */
public class DmViewPager extends ViewPager {
    public DmViewPager(@NonNull Context context) {
        this(context, null);
    }

    public DmViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}
