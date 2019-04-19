package com.yang.dm.mvp.presenter;


import com.yang.dm.mvp.contract.VideoPagerContract;
import com.yang.dm.mvp.model.OpenResponse;
import com.yang.dm.mvp.model.VideoDetails;
import com.yang.dm.mvp.model.VideoDiscovery;
import com.yang.dm.mvp.model.VideoRecommend;
import com.yang.dm.mvp.model.VideoResponse;
import com.yang.dm.remote.DataManager;
import com.yang.dm.utils.BannerHelper;
import com.yang.sdk.mvp.BasePresenter;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Describe: 视频类Presenter
 * Created by Yang on 2019/1/22.
 */
public class VideoPagerPresenter extends BasePresenter<VideoPagerContract.View> implements VideoPagerContract.Presenter {

    private DataManager mDataManager;
    private Call<OpenResponse<VideoDetails>> mCall;
    private Call<VideoResponse<VideoDiscovery>> mDsCall;
    private Call<VideoResponse<VideoRecommend>> mRecCall;

    @Inject
    public VideoPagerPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @Override
    public void getVideos(@NonNull List<VideoDetails> data, String aplUrl) {
        mCall = mDataManager.getVideos(aplUrl);
        mCall.enqueue(new Callback<OpenResponse<VideoDetails>>() {
            @Override
            public void onResponse(@NonNull Call<OpenResponse<VideoDetails>> call, @NonNull Response<OpenResponse<VideoDetails>> response) {
                if (response.isSuccessful()) {
                    data.addAll(Objects.requireNonNull(response.body()).getResult());
                    getView().setVideos();
                }
            }

            @Override
            public void onFailure(@NonNull Call<OpenResponse<VideoDetails>> call, @NonNull Throwable t) {
                getView().showErrorMsg(t.getMessage());
            }
        });
    }

    @Override
    public void getDsVideos(@NonNull List<VideoDiscovery> data, String aplUrl) {
        mDsCall = mDataManager.getDsVideos(aplUrl);
        mDsCall.enqueue(new Callback<VideoResponse<VideoDiscovery>>() {
            @Override
            public void onResponse(@NonNull Call<VideoResponse<VideoDiscovery>> call, @NonNull Response<VideoResponse<VideoDiscovery>> response) {
                if (response.isSuccessful()) {
                    data.addAll(Objects.requireNonNull(response.body()).getItemList());
                    getView().setDsVideos();
                }
            }

            @Override
            public void onFailure(@NonNull Call<VideoResponse<VideoDiscovery>> call, @NonNull Throwable t) {
                getView().showErrorMsg(t.getMessage());
            }
        });
    }

    @Override
    public void getRecVideos(String aplUrl) {
        mRecCall = mDataManager.getRcVideos(aplUrl);
        mRecCall.enqueue(new Callback<VideoResponse<VideoRecommend>>() {
            @Override
            public void onResponse(@NonNull Call<VideoResponse<VideoRecommend>> call, @NonNull Response<VideoResponse<VideoRecommend>> response) {
                if (response.isSuccessful()) {
                    getView().setRecVideos(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<VideoResponse<VideoRecommend>> call, @NonNull Throwable t) {
                getView().showErrorMsg(t.getMessage());
            }
        });
    }

    /**
     * @param banner
     * @param discovery
     */
    public void setTopBanner(@NonNull Banner banner, VideoDiscovery discovery) {
        List<String> imgRes = new ArrayList<>();
        List<VideoDiscovery.DataBeanX.ItemListBean> itemList = discovery.getData().getItemList();
        for (int i = 0; i < itemList.size(); i++) {
            imgRes.add(itemList.get(i).getData().getImage());
        }
        BannerHelper.initBanner(banner, imgRes, null, true);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mCall != null)
            mCall.cancel();
        if (mDsCall != null)
            mDsCall.cancel();
        if (mRecCall != null)
            mRecCall.cancel();
    }
}
