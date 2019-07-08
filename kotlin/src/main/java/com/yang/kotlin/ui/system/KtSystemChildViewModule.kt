package com.yang.kotlin.ui.system

import androidx.lifecycle.MutableLiveData
import com.yang.kotlin.base.KotlinViewModule
import com.yang.kotlin.model.bean.BaseListModel
import com.yang.kotlin.model.bean.ArticleModel
import com.yang.kotlin.model.repository.SystemChildRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Describe: java文件说明
 * Created by Yang on 2019/7/5  10:08
 */
class KtSystemChildViewModule : KotlinViewModule() {
    private val mRepository by lazy { SystemChildRepository() }
    val mArticleListModel: MutableLiveData<BaseListModel<ArticleModel>> = MutableLiveData()


    fun getSystemChild(page:Int,cid: Int) {
        launch {
            val result = withContext(Dispatchers.IO) { mRepository.getSystemChild(page,cid) }
            executeResponse(result, { mArticleListModel.value = result.data }, {})
        }
    }

}