package com.yang.dm.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.yang.dm.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

/**
 * Describe:
 * Created by Yang on 2019/1/23.
 */
public class DmCardView extends CardView {
    public DmCardView(@NonNull Context context) {
        this(context, null);
    }

    public DmCardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, null, 0);
    }

    public DmCardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
