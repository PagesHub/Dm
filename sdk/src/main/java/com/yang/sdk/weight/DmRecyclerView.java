package com.yang.sdk.weight;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Describe: 去掉滚动条和边界
 * Created by Yang on 2019/7/2  11:53
 */
public class DmRecyclerView extends RecyclerView {
    public DmRecyclerView(Context context) {
        this(context, null);
    }

    public DmRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DmRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        basicSettings();
    }
    /**
     * 部分设置
     */
    private void basicSettings() {
        //不显示滚动条
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        //不显示拉动边界
        setOverScrollMode(OVER_SCROLL_NEVER);
    }
}
