package com.yang.sdk.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;

import androidx.appcompat.widget.AppCompatRadioButton;

/**
 * Describe:  文字和自定义图片居中显示
 * Created by Yang on 2019/4/15.
 */
public class DrawableCenterRadioButton extends AppCompatRadioButton {

    public DrawableCenterRadioButton(Context context) {
        super(context);
    }

    public DrawableCenterRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawableCenterRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable[] drawables = getCompoundDrawables();
        Drawable drawable = drawables[0];
        if (drawable != null) {
            int gravity = getGravity();
            int left = 0;
            if (gravity == Gravity.CENTER) {
                left = ((int) (getWidth() - drawable.getIntrinsicWidth() - getPaint().measureText(getText().toString()))
                        / 2);
            }
            drawable.setBounds(left, 0, left + drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        }
        Drawable drawableRight = drawables[2];
        if (drawableRight != null) {
            //获取文字的宽度
            float textWidth = getPaint().measureText(getText().toString());
            int drawablePadding = getCompoundDrawablePadding();//获取设置的DrawablePadding值
            int drawableWidth = 0;
            drawableWidth = drawableRight.getIntrinsicWidth();//获取图片实际宽度
            float bodyWidth = textWidth + drawableWidth + drawablePadding;
            int y = getWidth();
            canvas.translate((getWidth() - bodyWidth) / 2, 0);
        }
        super.onDraw(canvas);

    }
}
