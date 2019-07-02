package com.yang.sdk.utils.animation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yang.sdk.utils.DisplayUtils;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;


/**
 * Describe:
 * Created by Yang on 2019/3/25.
 */
public class FabAnimator {
    private FabAnimatorListener mFabAnimatorListener;
    //动画tag
    private Group mTagGroup;
    // 圆的半径
    private int mRadius;
    // FloatingActionButton宽度和高度，宽高一样
    private int mWidth;

    public FabAnimator(Context context, FabAnimatorListener mFabAnimatorListener, Group mTagGroup, int mRadius, int mWidth) {
        this.mFabAnimatorListener = mFabAnimatorListener;
        this.mTagGroup = mTagGroup;
        this.mRadius = DisplayUtils.dp2px(context, mRadius);
        this.mWidth = mWidth;
    }

    /**
     * 获取ValueAnimator
     *
     * @param button  FloatingActionButton
     * @param reverse 开始还是隐藏
     * @param group   tagGroup 目标
     * @param group   Group    当前
     * @param angle   angle 转动的角度
     * @return ValueAnimator
     */
    public ValueAnimator getValueAnimator(final FloatingActionButton button, final boolean reverse, final Group group, final int angle) {
        ValueAnimator animator;
        if (reverse)
            animator = ValueAnimator.ofFloat(1, 0);
        else
            animator = ValueAnimator.ofFloat(0, 1);
        animator.addUpdateListener(animation -> {
            float v = (float) animation.getAnimatedValue();
            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) button.getLayoutParams();
            params.circleRadius = (int) (mRadius * v);
            params.circleAngle = 270f + angle * v;
            params.width = (int) (mWidth * v);
            params.height = (int) (mWidth * v);
            button.setLayoutParams(params);
        });
        animator.addListener(new SimpleAnimation() {
            @Override
            public void onAnimationStart(Animator animation) {
                group.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (group == mTagGroup && reverse) {
                    mFabAnimatorListener.onAnimatorEnd();
                }
            }
        });
        animator.setDuration(200);
        animator.setInterpolator(new DecelerateInterpolator());
        return animator;
    }

    abstract class SimpleAnimation implements Animator.AnimatorListener {
        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {

        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    }

    /**
     * 动画结束回调
     */
    public interface FabAnimatorListener {
        void onAnimatorEnd();
    }
}
