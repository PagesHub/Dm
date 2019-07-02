package com.yang.main.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yang.main.R;

/**
 * Describe: FloatingActionButton
 * Created by Yang on 2019/1/29.
 */
public class MFloatingActionButton extends FloatingActionButton {
    public MFloatingActionButton(Context context) {
        this(context, null);
    }

    public MFloatingActionButton(Context context, AttributeSet attrs) {
        this(context, attrs, R.style.Widget_Design_FloatingActionButton);
    }

    public MFloatingActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        basicSettings();
    }

    /**
     * 部分设置
     */
    private void basicSettings() {
        setClickable(true);
        setScaleType(ScaleType.CENTER_CROP);
    }
}
