package com.yang.kotlin.ui.project

import androidx.lifecycle.MutableLiveData
import com.yang.kotlin.base.KotlinViewModule
import com.yang.kotlin.model.bean.BaseListModel
import com.yang.kotlin.model.bean.ProjectModel
import com.yang.kotlin.model.bean.ProjectTypeModel
import com.yang.kotlin.model.repository.ProjectRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Describe: java文件说明
 * Created by Yang on 2019/6/21  17:54
 */
class KtProjectViewModule : KotlinViewModule() {

    private val mRepository: ProjectRepository by lazy { ProjectRepository() }

    val mProjectTypeModel: MutableLiveData<List<ProjectTypeModel>> = MutableLiveData()

    val mProjectModelList: MutableLiveData<BaseListModel<ProjectModel>> = MutableLiveData()


    fun getProjectType() {
        launch {
            val result = withContext(Dispatchers.IO) { mRepository.getProjectType() }
            executeResponse(result, { mProjectTypeModel.value = result.data }, {})
        }
    }

    fun getProjectList(page: Int, cid: Int) {
        launch {
            val result = withContext(Dispatchers.IO) { mRepository.getProjectList(page, cid) }
            executeResponse(result, { mProjectModelList.value = result.data }, {})
        }
    }

}