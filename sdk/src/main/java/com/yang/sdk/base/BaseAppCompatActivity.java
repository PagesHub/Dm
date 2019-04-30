package com.yang.sdk.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.yang.sdk.R;
import com.yang.sdk.weight.loading.VaryViewController;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Describe:  Activity基类
 * Created by Yang on 2019/4/15.
 */
public abstract class BaseAppCompatActivity extends AppCompatActivity {
    /**
     * Context
     */
    protected Context mContext = null;
    /**
     * loading view controller
     */
    private VaryViewController mVaryViewController = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(0, R.anim.pager_enter_animation);
        Bundle extras = getIntent().getExtras();
        if (null != extras) {
            getBundleExtras(extras);
        }
        mContext = this;
        BaseAppManager.getInstance().addActivity(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        if (null != getLoadingTargetView()) {
            mVaryViewController = new VaryViewController(getLoadingTargetView(), mContext);
        }
    }

    /**
     * 泛型简化findViewById
     *
     * @param resId
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int resId) {
        return (T) super.findViewById(resId);
    }

    /**
     * init view
     * 子类实现 控件绑定、视图初始化等内容
     */
    protected abstract void initView();

    /**
     * get loading target view
     */
    protected abstract View getLoadingTargetView();

    /**
     * get bundle data
     *
     * @param extras
     */
    protected abstract void getBundleExtras(Bundle extras);

    /**
     * startActivity
     *
     * @param clazz
     */
    protected void readyGo(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    /**
     * startActivity with bundle
     *
     * @param clazz
     * @param bundle
     */
    protected void readyGo(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * startActivity then finish
     *
     * @param clazz
     */
    protected void readyGoThenKill(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        finish();
    }

    /**
     * startActivity with bundle then finish
     *
     * @param clazz
     * @param bundle
     */
    protected void readyGoThenKill(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        finish();
    }

    protected void readyGoThenKillAll(Class<?> clazz) {
        Intent intent = new Intent(this, clazz)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    /**
     * startActivityForResult
     *
     * @param clazz
     * @param requestCode
     */
    protected void readyGoForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, requestCode);
    }

    /**
     * startActivityForResult with bundle
     *
     * @param clazz
     * @param requestCode
     * @param bundle
     */
    protected void readyGoForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mVaryViewController != null)
            mVaryViewController.showLoading(false);
        BaseAppManager.getInstance().removeActivity(this);
    }

    @Override
    public void finish() {
        super.finish();
        BaseAppManager.getInstance().removeActivity(this);
        overridePendingTransition(0, R.anim.pager_exit_animation);
    }

    /**
     * 点击空白位置 隐藏软键盘
     */
    public boolean onTouchEvent(MotionEvent event) {
        if (null != this.getCurrentFocus()) {
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            assert mInputMethodManager != null;
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED);
        }
        return super.onTouchEvent(event);
    }


    /**
     * toggle show loading
     *
     * @param toggle
     */
    protected void toggleShowLoading(boolean toggle) {
        if (mVaryViewController == null) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }
        mVaryViewController.showLoading(toggle);
    }

    /**
     * toggle show empty
     *
     * @param toggle
     */
    protected void toggleShowEmpty(boolean toggle, String msg, int drawableId, View.OnClickListener onClickListener) {
        if (mVaryViewController == null) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }
        if (toggle) {
            mVaryViewController.showEmpty(msg, drawableId, onClickListener);
        } else {
            mVaryViewController.restore();
        }
    }

    /**
     * toggle show error
     *
     * @param toggle
     */
    protected void toggleShowError(boolean toggle, String msg, int drawableId, View.OnClickListener onClickListener) {
        if (mVaryViewController == null) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }
        if (toggle) {
            mVaryViewController.showError(msg, drawableId, onClickListener);
        } else {
            mVaryViewController.restore();
        }
    }

    /**
     * toggle show network error
     *
     * @param toggle
     */
    protected void toggleNetworkError(boolean toggle, int drawableId, View.OnClickListener onClickListener) {
        if (mVaryViewController == null) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }
        if (toggle) {
            mVaryViewController.showNetworkError(drawableId, onClickListener);
        } else {
            mVaryViewController.restore();
        }
    }
}
