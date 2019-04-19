package com.yang.dm.mvp.contract;


import com.yang.dm.mvp.model.Read;
import com.yang.sdk.mvp.BaseContract;

import java.util.List;

import io.reactivex.annotations.NonNull;

/**
 * Describe:
 * Created by Yang on 2019/1/29.
 */
public interface ReadContract {

    interface View extends BaseContract.BaseView {

        void setReadInfo();

    }

    interface Presenter extends BaseContract.Presenter<View> {
        void getReadInfo(@NonNull List<Read> data, @NonNull String id, int page);
    }
}
