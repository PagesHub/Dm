package com.yang.kotlin.model.repository

import com.yang.kotlin.base.KotlinRepository
import com.yang.kotlin.base.KotlinResponse
import com.yang.kotlin.model.api.RetrofitClient
import com.yang.kotlin.model.bean.BaseListModel
import com.yang.kotlin.model.bean.ArticleModel

/**
 * Describe: java文件说明
 * Created by Yang on 2019/7/8  10:37
 */
class SystemChildRepository : KotlinRepository() {

    suspend fun getSystemChild(page:Int,cid: Int): KotlinResponse<BaseListModel<ArticleModel>> {
        return apiCall { RetrofitClient.service.getSystemChild(page,cid) }
    }
}