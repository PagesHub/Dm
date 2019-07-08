package com.yang.kotlin.ui.home

import androidx.lifecycle.MutableLiveData
import com.yang.kotlin.base.KotlinViewModule
import com.yang.kotlin.model.bean.BaseListModel
import com.yang.kotlin.model.bean.ArticleModel
import com.yang.kotlin.model.bean.BannerModel
import com.yang.kotlin.model.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Describe: java文件说明
 * Created by Yang on 2019/6/21  17:47
 */
class KtHomeViewModule : KotlinViewModule() {
    private val mRepository by lazy { HomeRepository() }
    val mBanners: MutableLiveData<List<BannerModel>> = MutableLiveData()
    val mArticleListModel: MutableLiveData<BaseListModel<ArticleModel>> = MutableLiveData()

    fun getBanners() {
        launch {
            val result = withContext(Dispatchers.IO) { mRepository.getBanner() }
            executeResponse(result, { mBanners.value = result.data }, {})
        }
    }

    fun getArticleList(page: Int) {
        launch {
            val result = withContext(Dispatchers.IO) { mRepository.getAticleList(page) }
            executeResponse(result, { mArticleListModel.value = result.data }, {})
        }
    }
}