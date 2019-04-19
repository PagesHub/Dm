package com.yang.dm.mvp.presenter;


import com.yang.dm.mvp.contract.VideoContract;
import com.yang.dm.mvp.model.OpenResponse;
import com.yang.dm.mvp.model.VideoTab;
import com.yang.dm.remote.DataManager;
import com.yang.sdk.mvp.BasePresenter;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Describe:
 * Created by Yang on 2019/1/31.
 */
public class VideoPresenter extends BasePresenter<VideoContract.View> implements VideoContract.Presenter {
    private DataManager mDataManager;
    private Call<OpenResponse<VideoTab>> call;

    @Inject
    public VideoPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void getVideoTab(@NonNull List<VideoTab> data) {
        call = mDataManager.getVideoTab("https://api.apiopen.top/videoHomeTab");
        call.enqueue(new Callback<OpenResponse<VideoTab>>() {
            @Override
            public void onResponse(@NonNull Call<OpenResponse<VideoTab>> call, @NonNull Response<OpenResponse<VideoTab>> response) {
                if (response.isSuccessful()) {
                    data.addAll(Objects.requireNonNull(response.body()).getResult());
                    getView().setVideoTab();
                }
            }
            @Override
            public void onFailure(@NonNull Call<OpenResponse<VideoTab>> call, @NonNull Throwable t) {
                getView().showErrorMsg(t.getMessage());
            }
        });

    }

    @Override
    public void detachView() {
        super.detachView();
        if (call != null)
            call.cancel();
    }

}
