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

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Describe:
 * Created by Yang on 2019/1/22.
 */
public interface APIService {

    @GET("data/{category}/10/{page}")
    Observable<BaseResponse<GanHuo>> getGanHuo(@Path("category") String category, @Path("page") int page);

    @GET("xiandu/categories")
    Observable<BaseResponse<ReadClassify>> getReadClassify();

    @GET("xiandu/category/{child}")
    Observable<BaseResponse<ReadClassifyChild>> getReadClassifyChild(@Path("child") String child);

    @GET("xiandu/data/id/{id}/count/10/page/{page}")
    Observable<BaseResponse<Read>> getReadInfo(@Path("id") String id, @Path("page") int page);

    @GET("data/休息视频/10/{page}")
    Observable<BaseResponse<GanHuo>> getVideos(@Path("page") int page);

    @GET
    Call<OpenResponse<VideoTab>> getVideoTab(@Url String url);

    @GET
    Call<OpenResponse<VideoDetails>> getVideos(@Url String url);

    @GET
    Call<VideoResponse<VideoDiscovery>> getDsVideos(@Url String url);

    @GET
    Call<VideoResponse<VideoRecommend>> getRcVideos(@Url String url);
}
