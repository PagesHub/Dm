package com.yang.wandroid.model.api

import com.yang.wandroid.base.WanResponse
import com.yang.wandroid.model.bean.ArticleList
import com.yang.wandroid.model.bean.BannerKt
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author        Yang
 * Description    java类作用描述
 * CreateDate     2019/5/17 10:55
 */
interface WApiService {

    companion object {
        const val BASE_URL = "https://www.wanandroid.com"
    }

    @GET("/article/list/{page}/json")
    fun getArticleList(@Path("page") page: Int): Deferred<WanResponse<ArticleList>>

    @GET("/banner/json")
    fun getBanner(): Deferred<WanResponse<List<BannerKt>>>
}