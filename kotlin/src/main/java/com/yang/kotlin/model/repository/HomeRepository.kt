package com.yang.kotlin.model.repository

import com.yang.kotlin.base.KotlinRepository
import com.yang.kotlin.base.KotlinResponse
import com.yang.kotlin.model.api.RetrofitClient
import com.yang.kotlin.model.bean.BaseListModel
import com.yang.kotlin.model.bean.ArticleModel
import com.yang.kotlin.model.bean.BannerModel

/**
 * Describe: java文件说明
 * Created by Yang on 2019/7/3  17:34
 */
class HomeRepository : KotlinRepository() {

    suspend fun getBanner(): KotlinResponse<List<BannerModel>> {
        return apiCall { RetrofitClient.service.getBanner() }
    }

    suspend fun getAticleList(page: Int): KotlinResponse<BaseListModel<ArticleModel>> {
        return apiCall { RetrofitClient.service.getArticleList(page) }
    }

}