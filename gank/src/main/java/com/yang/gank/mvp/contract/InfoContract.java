package com.yang.gank.mvp.contract;
import com.yang.sdk.mvp.BaseContract;



/**
 * Describe:
 * Created by Yang on 2019/1/25.
 */
public interface InfoContract {
    interface View extends BaseContract.BaseView {

    }

    interface Presenter extends BaseContract.Presenter<InfoContract.View> {
    }
}
