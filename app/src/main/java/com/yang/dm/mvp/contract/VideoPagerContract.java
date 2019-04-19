package com.yang.dm.mvp.contract;


import com.yang.dm.mvp.model.VideoDetails;
import com.yang.dm.mvp.model.VideoDiscovery;
import com.yang.dm.mvp.model.VideoRecommend;
import com.yang.dm.mvp.model.VideoResponse;
import com.yang.sdk.mvp.BaseContract;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * Describe:
 * Created by Yang on 2019/1/31.
 */
public interface VideoPagerContract {

    interface View extends BaseContract.BaseView {
        void setVideos();

        void setDsVideos();

        void setRecVideos(VideoResponse<VideoRecommend> recVideos);
    }

    interface Presenter extends BaseContract.Presenter<VideoPagerContract.View> {
        void getVideos(@NonNull List<VideoDetails> data, String aplUrl);

        void getDsVideos(@NonNull List<VideoDiscovery> data, String aplUrl);

        void getRecVideos(String aplUrl);
    }
}
