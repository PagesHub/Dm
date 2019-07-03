package com.yang.kotlin.model.api

import com.yang.kotlin.base.KotlinResponse
import com.yang.kotlin.model.bean.BannerModel
import retrofit2.http.GET

/**
 * Describe: j玩Android Api接口
 * Created by Yang on 2019/7/3  17:36
 */
interface WAService {

    companion object {
        const val BASE_URL = "https://www.wanandroid.com"
    }

    @GET("/banner/json")
    suspend fun getBanner(): KotlinResponse<List<BannerModel>>

}