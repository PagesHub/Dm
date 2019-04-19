package com.yang.sdk.mvp;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.yang.sdk.R;
import com.yang.sdk.app.BaseApplication;
import com.yang.sdk.base.BaseAppCompatActivity;
import com.yang.sdk.dagger.BaseComponent;
import com.zhangyue.we.x2c.X2C;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import butterknife.ButterKnife;

/**
 * Describe:  MVP Activity基类
 * Created by Yang on 2019/4/15.
 */
public abstract class BaseActivity extends BaseAppCompatActivity implements BaseContract.BaseView, View.OnClickListener {
    protected Toolbar mToolbar;
    protected TextView mTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (setX2C())
            X2C.setContentView(this, bindLayout());
        else setContentView(bindLayout());
        //注册ButterKnife
        ButterKnife.bind(this);
        initView();
        setListener();
    }

    /**
     * @return 是否以X2C方式加载布局
     */
    protected boolean setX2C() {
        return true;
    }

    /**
     * @return 绑定布局
     */
    protected abstract int bindLayout();

    /**
     * init view
     * 子类实现 控件绑定、视图初始化等内容
     */
    protected abstract void initView();

    /**
     * View点击
     * 禁止使用@OnClick(会使fastClick()无效)
     **/
    protected void widgetClick(View v) {

    }

    /**
     * 泛型简化findViewById
     *
     * @param resId
     * @param <T>
     * @return
     */
    @Override
    public <T extends View> T getView(int resId) {
        return super.getView(resId);
    }

    /**
     * [设置监听] 1秒钟只能点一次
     */
    protected void setListener() {
    }

    @Override
    public void onClick(View v) {
        if (fastClick())
            widgetClick(v);
    }

    /**
     * [防止快速点击]
     *
     * @return
     */
    public static final int MIN_CLICK_DELAY_TIME = 1000;
    private long lastClickTime = 0;

    private boolean fastClick() {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            return true;
        }
        lastClickTime = currentTime;
        return false;
    }

    /**
     * 设置Toolbar标题
     */
    protected void setToolbarTitle(@NonNull String title, int menuRes, boolean goBack) {
        mToolbar = getView(R.id.toolbar);
        mToolbar.setTitle("");
        mTitle = getView(R.id.txv_toolbar_title);
        mTitle.setText(title);
        mToolbar.setNavigationOnClickListener(v -> finish());
        if (menuRes != 0)
            mToolbar.inflateMenu(menuRes);
        if (goBack)
            mToolbar.setNavigationOnClickListener(v -> finish());
        else mToolbar.setNavigationIcon(null);
    }

    @NonNull
    protected Toolbar getToolbar() {
        return mToolbar;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
    }

    @Override
    public void showLoading() {
        toggleShowLoading(true);
    }

    @Override
    public void hideLoading() {
        toggleShowLoading(false);
    }

    @Override
    public void showError() {
    }

    @Override
    public void showErrorMsg(String errorMessage) {

    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    /**
     * 获取当前activity名称
     *
     * @return
     */
    public String getActivityName() {
        return getClass().getSimpleName();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * @return
     */
    protected BaseComponent getBaseComponent() {
        return BaseApplication.getBaseComponent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

