package com.yang.kotlin.model.api

import com.yang.kotlin.base.KotlinResponse
import com.yang.kotlin.model.bean.ArticleListModel
import com.yang.kotlin.model.bean.BannerModel
import com.yang.kotlin.model.bean.SystemModel
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Describe: j玩Android Api接口
 * Created by Yang on 2019/7/3  17:36
 */
interface WAService {

    companion object {
        const val BASE_URL = "https://www.wanandroid.com"
    }

    //首页Banner
    @GET("/banner/json")
    suspend fun getBanner(): KotlinResponse<List<BannerModel>>

    //文章列表
    @GET("/article/list/{page}/json")
    suspend fun getArticleList(@Path("page") page: Int): KotlinResponse<ArticleListModel>

    //体系数据
    @GET("/tree/json")
    suspend fun getSystemTree(): KotlinResponse<List<SystemModel>>

}