package com.yang.dm.mvp.contract;


import com.yang.sdk.mvp.BaseContract;

/**
 * Describe:
 */
public interface MainContract {

    interface View extends BaseContract.BaseView {
    }

    interface Presenter extends BaseContract.Presenter<MainContract.View> {
    }
}
