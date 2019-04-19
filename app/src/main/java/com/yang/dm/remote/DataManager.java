package com.yang.dm.remote;


import com.yang.dm.mvp.model.BaseResponse;
import com.yang.dm.mvp.model.GanHuo;
import com.yang.dm.mvp.model.OpenResponse;
import com.yang.dm.mvp.model.Read;
import com.yang.dm.mvp.model.ReadClassify;
import com.yang.dm.mvp.model.ReadClassifyChild;
import com.yang.dm.mvp.model.VideoDetails;
import com.yang.dm.mvp.model.VideoDiscovery;
import com.yang.dm.mvp.model.VideoRecommend;
import com.yang.dm.mvp.model.VideoResponse;
import com.yang.dm.mvp.model.VideoTab;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Call;

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


    /**
     * @param child 分类
     * @return
     */
    public Observable<BaseResponse<ReadClassifyChild>> getReadClassifyChild(String child) {

        return mAPIService.getReadClassifyChild(child);
    }

    /**
     * @param id   闲读目标Id
     * @param page 页数
     * @return
     */
    public Observable<BaseResponse<Read>> getReadInfo(String id, int page) {
        return mAPIService.getReadInfo(id, page);
    }

    /**
     * 休息视频
     *
     * @param page 页数
     * @return
     */
    public Observable<BaseResponse<GanHuo>> getVideos(int page) {
        return mAPIService.getVideos(page);
    }

    /**
     * 视频大纲获取接口
     *
     * @return
     */
    public Call<OpenResponse<VideoTab>> getVideoTab(String url) {
        return mAPIService.getVideoTab(url);
    }

    /**
     * 视频分类详情接口
     *
     * @return
     */
    public Call<OpenResponse<VideoDetails>> getVideos(String url) {
        return mAPIService.getVideos(url);
    }

    /**
     * 视频分类发现接口
     *
     * @return
     */
    public Call<VideoResponse<VideoDiscovery>> getDsVideos(String url) {
        return mAPIService.getDsVideos(url);
    }

    /**
     * 视频分类推荐接口
     *
     * @return
     */
    public Call<VideoResponse<VideoRecommend>> getRcVideos(String url) {
        return mAPIService.getRcVideos(url);
    }

}
