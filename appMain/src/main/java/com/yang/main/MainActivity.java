package com.yang.main;


import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.view.View;
import android.widget.FrameLayout;

import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.yang.main.base.MBaseActivity;
import com.yang.main.widget.MFloatingActionButton;
import com.yang.sdk.arounter.ARouterConstant;
import com.yang.sdk.arounter.ARouterUtils;
import com.yang.sdk.arounter.providers.ModuleGankService;
import com.yang.sdk.utils.PerfectClickListener;
import com.yang.sdk.utils.animation.FabAnimator;
import com.zhangyue.we.x2c.ano.Xml;

import butterknife.BindView;

/**
 * Describe: java文件说明
 * Created by Yang on 2019/6/20  17:42
 */
@Xml(layouts = "activity_main")
public class MainActivity extends MBaseActivity implements FabAnimator.FabAnimatorListener {
    @BindView(R2.id.fragment_container)
    FrameLayout mFragmentContainer;
    @BindView(R2.id.fab_add)
    MFloatingActionButton mFabAdd;
    @BindView(R2.id.fab_like)
    MFloatingActionButton mFabLike;
    @BindView(R2.id.fab_write)
    MFloatingActionButton mFabWrite;
    @BindView(R2.id.fab_top)
    MFloatingActionButton mFabTop;
    @BindView(R2.id.gp_like)
    Group mGpLike;
    @BindView(R2.id.gp_write)
    Group mGpWrite;
    @BindView(R2.id.gp_top)
    Group mGpTop;

    @Autowired
    ModuleGankService mModuleGankService;

    /**
     * 动画集合，用来控制动画的有序播放
     */
    private AnimatorSet mAnimatorSet;
    private FabAnimator mFabAnimator;

    @Override
    protected boolean injectRouter() {
        return true;

    }
    @Override
    protected void onResume() {
        super.onResume();
        mFabAdd.post(() -> mFabAnimator = new FabAnimator(MainActivity.this, MainActivity.this, mGpLike, 60, mFabAdd.getMeasuredWidth()));

    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        //Fragment管理器
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.add(R.id.fragment_container, mModuleGankService.getModuleGankFragment());
        beginTransaction.commitAllowingStateLoss();

        mFabAdd.setOnClickListener(mClickListener);
        mFabLike.setOnClickListener(mClickListener);
        mFabWrite.setOnClickListener(mClickListener);
        mFabTop.setOnClickListener(mClickListener);
    }

    /**
     * 防双击点击事件
     */
    private final PerfectClickListener mClickListener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            int i = v.getId();
            if (i == R.id.fab_add) {
                initFabAnimator();
            } else if (i == R.id.fab_like) {
                ARouterUtils.navigation(ARouterConstant.MainPath.ACTIVITY_MAIN_FLUTTER);
            } else if (i == R.id.fab_write) {
                ARouterUtils.navigation(ARouterConstant.ChatPath.ACTIVITY_CHAT_MAIN);
            } else if (i == R.id.fab_top) {
                ARouterUtils.navigation(ARouterConstant.KotlinPath.ACTIVITY_KOTLIN_MAIN);
            }
        }
    };

    /**
     * 点击事件
     */
    private void initFabAnimator() {
        boolean is = mFabAnimator == null || mAnimatorSet != null && mAnimatorSet.isRunning();
        // 播放动画的时候不可以点击
        if (is) {
            return;
        }
        mAnimatorSet = new AnimatorSet();
        boolean visible = mFabLike.getVisibility() == View.VISIBLE;
        ValueAnimator likeAnimator = mFabAnimator.getValueAnimator(mFabLike, visible, mGpLike, 0);
        ValueAnimator writeAnimator = mFabAnimator.getValueAnimator(mFabWrite, visible, mGpWrite, 45);
        ValueAnimator topAnimator = mFabAnimator.getValueAnimator(mFabTop, visible, mGpTop, 90);
        mAnimatorSet.playSequentially(visible ? topAnimator : likeAnimator, writeAnimator, visible ? likeAnimator : topAnimator);
        mAnimatorSet.start();
    }

    @Override
    public void onAnimatorEnd() {
        mGpLike.setVisibility(View.GONE);
        mGpWrite.setVisibility(View.GONE);
        mGpTop.setVisibility(View.GONE);
    }
}
