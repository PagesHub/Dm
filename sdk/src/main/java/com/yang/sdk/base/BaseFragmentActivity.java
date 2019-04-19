package com.yang.sdk.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yang.sdk.weight.loading.VaryViewController;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

/**
 * Describe:  FragmentActivity基类
 * Created by Yang on 2019/4/15.
 */
public abstract class BaseFragmentActivity extends FragmentActivity {
    /**
     * context
     */
    protected Context mContext = null;

    /**
     * loading view controller
     */
    private VaryViewController mVaryViewController = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if (null != extras) {
            getBundleExtras(extras);
        }

        mContext = this;

        if (getContentViewLayoutID() != 0) {
            setContentView(getContentViewLayoutID());
        } else {
            throw new IllegalArgumentException("You must return a right contentView layout resource Id");
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        if (null != getLoadingTargetView()) {
            mVaryViewController = new VaryViewController(getLoadingTargetView(), mContext);
        }
    }

    @Override
    public void finish() {
        super.finish();
        BaseAppManager.getInstance().removeActivity(this);
    }

    /**
     * init all views
     */
    protected abstract void initView();

    /**
     * get bundle data
     *
     * @param extras
     */
    protected abstract void getBundleExtras(Bundle extras);

    /**
     * bind layout resource file
     *
     * @return id of layout resource
     */
    protected abstract int getContentViewLayoutID();

    /**
     * get loading target view
     */
    protected abstract View getLoadingTargetView();

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

    /**
     * toggle show loading
     *
     * @param toggle
     */
    protected void toggleShowLoading(boolean toggle) {
        if (null == mVaryViewController) {
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
        if (null == mVaryViewController) {
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
        if (null == mVaryViewController) {
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
        if (null == mVaryViewController) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }

        if (toggle) {
            mVaryViewController.showNetworkError(drawableId, onClickListener);
        } else {
            mVaryViewController.restore();
        }
    }
}
