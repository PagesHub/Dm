package com.yang.sdk.mvp;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Describe:  Presenter 基类
 * Created by Yang on 2019/4/15.
 */
public class BasePresenter<V extends BaseContract.BaseView> implements BaseContract.Presenter<V> {
    private Reference<V> mViewRef;
    private CompositeDisposable mCompositeSubscription;

    public BasePresenter() {
        mCompositeSubscription = new CompositeDisposable();
    }

    /**
     * 绑定V层
     *
     * @param view
     */
    @Override
    public void attachView(V view) {
        mViewRef = new WeakReference<>(view);
    }

    /**
     * 解除绑定V层
     */
    @Override
    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
        //取消订阅
        if (mCompositeSubscription != null) {
            mCompositeSubscription.dispose();
        }
    }

    /**
     * 判断V是否绑定
     *
     * @return
     */
    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    /**
     * 获取V层
     *
     * @return
     */
    public V getView() {
        return mViewRef != null ? mViewRef.get() : null;
    }
}
