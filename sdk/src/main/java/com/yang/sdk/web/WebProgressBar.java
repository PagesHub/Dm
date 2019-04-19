package com.yang.sdk.web;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.yang.sdk.R;


/**
 * Describe: WebProgressBar
 * Created by Yang on 2019/4/15.
 */
public class WebProgressBar extends ProgressBar {
    public WebProgressBar(Context context) {
        this(context, null);
    }

    public WebProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.progressBarStyle);
    }

    public WebProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, R.style.WebProgressBarStyle);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public WebProgressBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        basicSettings();
    }

    /**
     * 部分设置
     */
    private void basicSettings() {
    }
}