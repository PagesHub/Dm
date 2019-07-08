package com.yang.kotlin.model.repository

import com.yang.kotlin.base.KotlinRepository
import com.yang.kotlin.base.KotlinResponse
import com.yang.kotlin.model.api.RetrofitClient
import com.yang.kotlin.model.bean.BaseListModel
import com.yang.kotlin.model.bean.ProjectModel
import com.yang.kotlin.model.bean.ProjectTypeModel

/**
 * Describe: java文件说明
 * Created by Yang on 2019/7/8  15:38
 */
class ProjectRepository : KotlinRepository() {

    suspend fun getProjectType(): KotlinResponse<List<ProjectTypeModel>> {
        return apiCall { RetrofitClient.service.getProjectType() }
    }


    suspend fun getProjectList(page: Int, cid: Int): KotlinResponse<BaseListModel<ProjectModel>> {
        return apiCall { RetrofitClient.service.getProjectList(page, cid) }
    }
}