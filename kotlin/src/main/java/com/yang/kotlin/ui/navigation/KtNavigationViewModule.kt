package com.yang.kotlin.ui.navigation

import androidx.lifecycle.MutableLiveData
import com.yang.kotlin.base.KotlinViewModule
import com.yang.kotlin.model.bean.NavigationModel
import com.yang.kotlin.model.repository.NavigationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Describe: java文件说明
 * Created by Yang on 2019/6/21  17:52
 */
class KtNavigationViewModule : KotlinViewModule() {

    private val mRepository: NavigationRepository by lazy { NavigationRepository() }

    val mNavigationModel: MutableLiveData<List<NavigationModel>> = MutableLiveData()


    fun getNavigation() {
        launch {
            val result = withContext(Dispatchers.IO) { mRepository.getNavigation() }
            executeResponse(result, { mNavigationModel.value = result.data }, {})
        }
    }
}