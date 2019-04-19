package com.yang.sdk.weight;

import android.content.Context;
import android.util.AttributeSet;

import com.yang.sdk.R;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

/**
 * Describe:
 * Created by Yang on 2019/4/15.
 */
public class SdkToolBar extends Toolbar {
    public SdkToolBar(Context context) {
        this(context, null);
    }

    public SdkToolBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, R.attr.toolbarStyle);
    }

    public SdkToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
