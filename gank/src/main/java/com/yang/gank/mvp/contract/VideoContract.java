package com.yang.gank.mvp.contract;


import androidx.annotation.NonNull;

import com.yang.gank.mvp.model.VideoTab;
import com.yang.sdk.mvp.BaseContract;

import java.util.List;


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
