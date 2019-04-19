package com.yang.dm.mvp.contract;

import com.yang.dm.mvp.model.ReadClassifyChild;
import com.yang.sdk.mvp.BaseContract;

import java.util.List;

/**
 * Describe:
 * Created by Yang on 2019/1/21.
 */
public interface NewsPagerContract {
    interface View extends BaseContract.BaseView {

        void getReadClassifyChild();
    }

    interface Presenter extends BaseContract.Presenter<NewsPagerContract.View> {
        void getReadClassifyChild(List<ReadClassifyChild> data, String child);
    }
}
