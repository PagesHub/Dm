package com.yang.sdk.mvp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yang.sdk.app.BaseApplication;
import com.yang.sdk.base.BaseLazyFragment;
import com.yang.sdk.dagger.BaseComponent;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Describe:  MVP BaseFragment基类
 * Created by Yang on 2019/4/15.
 */
public abstract class BaseFragment extends BaseLazyFragment implements BaseContract.BaseView, View.OnClickListener {

    protected Activity mActivity;
    protected Context mContext;
    protected Unbinder mUnbinder;

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        mContext = getContext();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (bindLayout() != 0) {
            return inflater.inflate(bindLayout(), null);
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //注册ButterKnife
        mUnbinder = ButterKnife.bind(this, view);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = getActivity();
    }

    /**
     * @return 是否以X2C方式加载布局
     */
    protected boolean setX2C() {
        return true;
    }

    /**
     * [绑定布局]
     *
     * @return
     */
    protected abstract int bindLayout();

    /**
     * [设置监听] 1秒钟只能点一次
     */
    protected void setListener() {
    }

    /**
     * View点击
     **/
    public void widgetClick(View v) {

    }

    protected TextView setText(@IdRes int viewId, CharSequence value) {
        TextView view = getView(viewId);
        view.setText(value);
        return view;
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
    /*** 最小点击时间**/
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

    @Override
    protected View getLoadingTargetView() {
        return null;
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
    protected void onFirstUserVisible() {//do some initialized work or refresh data only once
    }

    @Override
    protected void onUserVisible() {//like the fragment's lifecycle method onResume()

    }

    @Override
    protected void onUserInvisible() {//like the fragment's lifecycle method onPause()
    }

    @Override
    public void showError() {

    }

    @Override
    public void showErrorMsg(String errorMessage) {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    /**
     * @return
     */
    protected BaseComponent getBaseComponent() {
        return BaseApplication.getBaseComponent();
    }

    /**
     * 获取当前Fragment名称
     *
     * @return
     */
    public String getFragmentName() {
        return getClass().getSimpleName();
    }
}
