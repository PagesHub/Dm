package com.yang.kotlin.model.bean

/**
 * Describe: java文件说明
 * Created by Yang on 2019/7/9  11:16
 */
data class NavigationModel(
    val articles: List<ArticleModel>,
    val cid: Int,
    val name: String
)
