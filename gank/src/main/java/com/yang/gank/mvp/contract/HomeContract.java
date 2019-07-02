package com.yang.gank.mvp.contract;


import com.yang.sdk.mvp.BaseContract;

/**
 * Describe:
 * Created by Yang on 2019/1/21.
 */
public interface HomeContract {
    interface View extends BaseContract.BaseView {
    }

    interface Presenter extends BaseContract.Presenter<HomeContract.View> {
    }
}
