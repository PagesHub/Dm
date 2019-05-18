package com.yang.wandroid.ui.home

import androidx.lifecycle.MutableLiveData
import com.yang.wandroid.base.BaseViewModel
import com.yang.wandroid.model.bean.ArticleList
import com.yang.wandroid.model.bean.BannerKt
import com.yang.wandroid.model.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author        Yang
 * Description    java类作用描述
 * CreateDate     2019/5/17 10:55
 */
class HomeViewModel : BaseViewModel() {

    private val mRepository by lazy { HomeRepository() }
    val mBanner: MutableLiveData<List<BannerKt>> = MutableLiveData()
    val mArticleList: MutableLiveData<ArticleList> = MutableLiveData()

    fun getBanners() {
        launch {
            val result = withContext(Dispatchers.IO) { mRepository.getBanners() }
            executeResponse(result, { mBanner.value = result.data }, {})
        }
    }

    fun getArticles(page: Int) {
        launch {
            val result = withContext(Dispatchers.IO) { mRepository.getArticles(page) }
            executeResponse(result, { mArticleList.value = result.data }, {})

        }
    }
}