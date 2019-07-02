package com.yang.gank.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.yang.gank.R;

/**
 * Describe: Cank条目CardView
 * Created by Yang on 2019/7/2  12:09
 */
public class GkCardView extends CardView {
    public GkCardView(@NonNull Context context) {
        this(context, null);
    }

    public GkCardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, null, 0);
    }

    public GkCardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        basicSettings();
    }

    /**
     * 部分设置
     */
    private void basicSettings() {
        setUseCompatPadding(true);
        setCardBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
    }
}
