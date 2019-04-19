package com.yang.dm.mvp.contract;

import com.yang.dm.mvp.model.ReadClassify;
import com.yang.sdk.mvp.BaseContract;

import java.util.List;

/**
 * Describe:
 * Created by Yang on 2019/1/25.
 */
public interface NewsContract {
    interface View extends BaseContract.BaseView {

        void setReadClassify();
    }

    interface Presenter extends BaseContract.Presenter<NewsContract.View> {
        void getReadClassify(List<ReadClassify> date);
    }
}
