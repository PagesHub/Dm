package com.yang.wandroid.model.repository

import com.yang.wandroid.base.BaseRepository
import com.yang.wandroid.base.WanResponse
import com.yang.wandroid.model.api.WARetrofitClient
import com.yang.wandroid.model.bean.ArticleList
import com.yang.wandroid.model.bean.BannerKt

/**
 * @author        Yang
 * Description    java类作用描述
 * CreateDate     2019/5/17 10:55
 */
class HomeRepository : BaseRepository() {

    suspend fun getBanners(): WanResponse<List<BannerKt>> {
        return apiCall { WARetrofitClient.service.getBanner().await() }
    }

    suspend fun getArticles(page: Int): WanResponse<ArticleList> {
        return apiCall { WARetrofitClient.service.getArticleList(page).await() }
    }
}