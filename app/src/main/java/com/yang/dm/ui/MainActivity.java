package com.yang.dm.ui;


import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.view.View;

import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.yang.chat.ChatMainActivity;
import com.yang.dm.R;
import com.yang.dm.base.BaseDmActivity;
import com.yang.dm.mvp.contract.MainContract;
import com.yang.dm.mvp.presenter.MainPresenter;
import com.yang.dm.widget.DmFloatingActionButton;
import com.yang.sdk.utils.PerfectClickListener;
import com.yang.sdk.utils.animation.FabAnimator;
import com.yang.wandroid.WAMainActivity;
import com.zhangyue.we.x2c.ano.Xml;

import javax.inject.Inject;

import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.FragmentManager;
import butterknife.BindView;
import cn.jzvd.Jzvd;

/**
  * @author        Yang
  * Description    java类作用描述
  * CreateDate     2019/4/26 11:15
 */
@Xml(layouts = "activity_main")
public class MainActivity extends BaseDmActivity implements MainContract.View, FabAnimator.FabAnimatorListener {
    @Inject
    MainPresenter mainPresenter;
    @BindView(R.id.bottom_nav)
    BottomNavigationViewEx mBottomNv;
    @BindView(R.id.fab_add)
    DmFloatingActionButton mFabAdd;
    @BindView(R.id.fab_like)
    DmFloatingActionButton mFabLike;
    @BindView(R.id.fab_write)
    DmFloatingActionButton mFabWrite;
    @BindView(R.id.fab_top)
    DmFloatingActionButton mFabTop;
    @BindView(R.id.gp_like)
    Group mGpLike;
    @BindView(R.id.gp_write)
    Group mGpWrite;
    @BindView(R.id.gp_top)
    Group mGpTop;
    /**
     * 动画集合，用来控制动画的有序播放
     */
    private AnimatorSet mAnimatorSet;
    private FabAnimator mFabAnimator;
    private FragmentManager mFragmentManager;

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
        // 依赖注入
        activityComponent.inject(this);
        mainPresenter.attachView(this);
        //Fragment管理器
        mFragmentManager = getSupportFragmentManager();
        //创建 fragment
        mainPresenter.initFragment(mFragmentManager);
        mainPresenter.selectFragment(mFragmentManager, 0);
        //设置底部
        setupBottomNavigationView();
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
            switch (v.getId()) {
                case R.id.fab_add:
                    initFabAnimator();
                    break;
                case R.id.fab_like:
                    readyGo(WAMainActivity.class);
                    break;
                case R.id.fab_write:
                    readyGo(ChatMainActivity.class);
                    break;
                case R.id.fab_top:
                    readyGo(FlutterActivity.class);
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 设置底部标题
     */
    private void setupBottomNavigationView() {
        mBottomNv.enableAnimation(false);
        mBottomNv.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        mBottomNv.setItemHorizontalTranslationEnabled(false);
        mBottomNv.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    mainPresenter.selectFragment(mFragmentManager, 0);
                    break;
                case R.id.news:
                    mainPresenter.selectFragment(mFragmentManager, 1);
                    break;
                case R.id.video:
                    mainPresenter.selectFragment(mFragmentManager, 2);
                    break;
                case R.id.info:
                    mainPresenter.selectFragment(mFragmentManager, 3);
                    break;
                default:
                    break;
            }
            return true;
        });
    }

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
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.resetAllVideos();
    }

    @Override
    public void onAnimatorEnd() {
        mGpLike.setVisibility(View.GONE);
        mGpWrite.setVisibility(View.GONE);
        mGpTop.setVisibility(View.GONE);
    }
}
