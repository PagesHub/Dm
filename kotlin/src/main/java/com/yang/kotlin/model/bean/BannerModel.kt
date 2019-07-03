package com.yang.kotlin.model.bean

/**
 * Describe: java文件说明
 * Created by Yang on 2019/7/3  16:45
 */data class BannerModel(
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String
)