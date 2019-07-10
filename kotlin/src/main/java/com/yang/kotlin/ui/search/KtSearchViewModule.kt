package com.yang.kotlin.ui.search

import androidx.lifecycle.MutableLiveData
import com.yang.kotlin.base.KotlinViewModule
import com.yang.kotlin.model.bean.ArticleModel
import com.yang.kotlin.model.bean.BaseListModel
import com.yang.kotlin.model.bean.HotKeyModel
import com.yang.kotlin.model.repository.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Describe: java文件说明
 * Created by Yang on 2019/7/10  10:42
 */
class KtSearchViewModule : KotlinViewModule() {

    private val mRepository: SearchRepository by lazy { SearchRepository() }

    val mSearchModel: MutableLiveData<BaseListModel<ArticleModel>> = MutableLiveData()
    var mHotKeyList: MutableLiveData<List<HotKeyModel>> = MutableLiveData()
    var mFriendList: MutableLiveData<List<HotKeyModel>> = MutableLiveData()

    fun query(page: Int, key: String) {
        launch {
            var result = withContext(Dispatchers.IO) { mRepository.doQuery(page, key) }
            executeResponse(result, { mSearchModel.value = result.data }, {})
        }
    }

    fun getHotKey() {
        launch {
            var result = withContext(Dispatchers.IO) { mRepository.getHotKey() }
            executeResponse(result, { mHotKeyList.value = result.data }, {})
        }
    }

    fun getFriend() {
        launch {
            var result = withContext(Dispatchers.IO) { mRepository.getFriend() }
            executeResponse(result, { mFriendList.value = result.data }, {})

        }
    }
}