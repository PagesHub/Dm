package com.yang.gank.remote;

import com.yang.gank.mvp.model.BaseResponse;
import com.yang.gank.mvp.model.GanHuo;
import com.yang.gank.mvp.model.ReadClassify;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Describe:
 * Created by Yang on 2019/1/22.
 */
public final class DataManager {

    private APIService mAPIService;

    @Inject
    public DataManager(APIService apiService) {
        this.mAPIService = apiService;
    }

    /**
     * 获取干货
     *
     * @param category 分类
     * @param page     页数
     * @return
     */
    public Observable<BaseResponse<GanHuo>> getGanHuo(String category, int page) {
        return mAPIService.getGanHuo(category, page);
    }

    /**
     * 获取闲读分类
     *
     * @return
     */
    public Observable<BaseResponse<ReadClassify>> getReadClassify() {
        return mAPIService.getReadClassify();
    }

}
