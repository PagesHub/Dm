package com.yang.kotlin.model.repository

import com.yang.kotlin.base.KotlinRepository
import com.yang.kotlin.base.KotlinResponse
import com.yang.kotlin.model.api.RetrofitClient
import com.yang.kotlin.model.bean.ArticleModel
import com.yang.kotlin.model.bean.BaseListModel
import com.yang.kotlin.model.bean.HotKeyModel

/**
 * Describe: java文件说明
 * Created by Yang on 2019/7/10  15:30
 */
class SearchRepository : KotlinRepository() {

    suspend fun doQuery(page: Int, key: String): KotlinResponse<BaseListModel<ArticleModel>> {
        return apiCall { RetrofitClient.service.doQuery(page, key) }
    }

    suspend fun getHotKey(): KotlinResponse<List<HotKeyModel>> {
        return apiCall { RetrofitClient.service.getHotKey() }
    }

    suspend fun getFriend(): KotlinResponse<List<HotKeyModel>> {
        return apiCall { RetrofitClient.service.getFriend() }
    }
}