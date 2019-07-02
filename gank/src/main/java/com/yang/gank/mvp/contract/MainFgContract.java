package com.yang.gank.mvp.contract;


import com.yang.sdk.mvp.BaseContract;

/**
 * Describe: GkMainFragment
 * Created by Yang on 2019/1/21.
 */
public interface MainFgContract {
    interface View extends BaseContract.BaseView {
    }

    interface Presenter extends BaseContract.Presenter<MainFgContract.View> {
    }
}
