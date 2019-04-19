package com.yang.dm.mvp.presenter;


import com.yang.dm.mvp.contract.HomeContract;
import com.yang.sdk.mvp.BasePresenter;

import javax.inject.Inject;

/**
 * Describe:   干货Presenter
 * Created by Yang on 2019/1/21.
 */
public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {
    @Inject
    public HomePresenter() {
    }
}
