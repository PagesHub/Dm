package com.yang.kotlin.model.repository

import com.yang.kotlin.base.KotlinRepository
import com.yang.kotlin.base.KotlinResponse
import com.yang.kotlin.model.api.RetrofitClient
import com.yang.kotlin.model.bean.NavigationModel

/**
 * Describe: java文件说明
 * Created by Yang on 2019/7/9  11:19
 */
class NavigationRepository : KotlinRepository() {

    suspend fun getNavigation(): KotlinResponse<List<NavigationModel>> {
        return apiCall { RetrofitClient.service.getNavigation() }
    }
}