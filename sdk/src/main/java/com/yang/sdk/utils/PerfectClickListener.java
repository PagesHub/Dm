package com.yang.sdk.utils;

import android.view.View;

import java.util.Calendar;

/**
 * Describe: 防止双击
 * Created by Yang on 2019/4/15.
 */
public abstract class PerfectClickListener  implements View.OnClickListener{

    private static final int MIN_CLICK_DELAY_TIME = 1000;
    private long lastClickTime = 0;
    private int id = -1;

    @Override
    public void onClick(View view) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        int mId = view.getId();
        if (id != mId) {
            id = mId;
            lastClickTime = currentTime;
            onNoDoubleClick(view);
            return;
        }
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            onNoDoubleClick(view);
        }
    }

    protected abstract void onNoDoubleClick(View v);

}
