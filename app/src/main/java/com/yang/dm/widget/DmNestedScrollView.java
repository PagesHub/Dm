package com.yang.dm.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;

/**
 * Describe:  LnNestedScrollView
 * Created by Yang on 2019/1/29.
 */
public class DmNestedScrollView extends NestedScrollView {
    public DmNestedScrollView(@NonNull Context context) {
        this(context, null);
    }

    public DmNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DmNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        basicSettings();
    }

    /**
     * 部分设置
     */
    private void basicSettings() {
        setFitsSystemWindows(true);
        setHorizontalScrollBarEnabled(true);
        setVerticalScrollBarEnabled(true);
    }
}
