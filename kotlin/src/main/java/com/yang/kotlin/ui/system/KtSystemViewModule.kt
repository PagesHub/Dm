package com.yang.kotlin.ui.system

import androidx.lifecycle.MutableLiveData
import com.yang.kotlin.base.KotlinViewModule
import com.yang.kotlin.model.bean.SystemModel
import com.yang.kotlin.model.repository.SystemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Describe: java文件说明
 * Created by Yang on 2019/6/21  17:56
 */
class KtSystemViewModule : KotlinViewModule() {
    private val mRepository by lazy { SystemRepository() }
    val mSystemModelList: MutableLiveData<List<SystemModel>> = MutableLiveData()


    fun getSystemTree() {
        launch {
            val result = withContext(Dispatchers.IO) { mRepository.getSystemTree() }
            executeResponse(result, { mSystemModelList.value = result.data }, {})
        }
    }
}