package com.yang.gank.remote;

import com.yang.gank.mvp.model.BaseResponse;
import com.yang.gank.mvp.model.GanHuo;
import com.yang.gank.mvp.model.ReadClassify;
import com.yang.gank.mvp.model.ReadClassifyChild;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

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

}
