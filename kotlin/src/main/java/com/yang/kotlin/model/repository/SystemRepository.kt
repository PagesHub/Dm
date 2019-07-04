package com.yang.kotlin.model.repository

import com.yang.kotlin.base.KotlinRepository
import com.yang.kotlin.base.KotlinResponse
import com.yang.kotlin.model.api.RetrofitClient
import com.yang.kotlin.model.bean.SystemModel

/**
 * Describe: java文件说明
 * Created by Yang on 2019/7/4  16:54
 */
class SystemRepository : KotlinRepository() {

    suspend fun getSystemTree(): KotlinResponse<List<SystemModel>> {
        return apiCall { RetrofitClient.service.getSystemTree() }
    }
}