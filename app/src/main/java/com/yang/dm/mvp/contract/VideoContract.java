package com.yang.dm.mvp.contract;


import com.yang.dm.mvp.model.VideoTab;
import com.yang.sdk.mvp.BaseContract;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * Describe:
 * Created by Yang on 2019/1/31.
 */
public interface VideoContract {

    interface View extends BaseContract.BaseView {

        void setVideoTab();

    }

    interface Presenter extends BaseContract.Presenter<VideoContract.View> {
        void getVideoTab(@NonNull List<VideoTab> data);
    }
}
