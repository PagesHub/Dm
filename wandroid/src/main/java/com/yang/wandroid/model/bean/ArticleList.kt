package com.yang.wandroid.model.bean

data class ArticleList(
        val curPage: Int,
        val datas: List<Article>,
        val offset: Int,
        val over: Boolean,
        val pageCount: Int,
        val size: Int,
        val total: Int
)