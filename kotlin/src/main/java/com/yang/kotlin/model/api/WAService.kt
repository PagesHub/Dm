package com.yang.kotlin.model.api

import com.yang.kotlin.base.KotlinResponse
import com.yang.kotlin.model.bean.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

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
    suspend fun getArticleList(@Path("page") page: Int): KotlinResponse<BaseListModel<ArticleModel>>

    //体系数据
    @GET("/tree/json")
    suspend fun getSystemTree(): KotlinResponse<List<SystemModel>>

    //知识体系下的文章
    @GET("/article/list/{page}/json")
    suspend fun getSystemChild(@Path("page") page: Int, @Query("cid") cid: Int): KotlinResponse<BaseListModel<ArticleModel>>

    //项目分类
    @GET("/project/tree/json")
    suspend fun getProjectType(): KotlinResponse<List<ProjectTypeModel>>

    // 项目列表数据
    @GET("/project/list/{page}/json")
    suspend fun getProjectList(@Path("page") page: Int, @Query("cid") cid: Int): KotlinResponse<BaseListModel<ProjectModel>>

    //导航数据
    @GET("/navi/json")
    suspend fun getNavigation(): KotlinResponse<List<NavigationModel>>

}