package com.yang.kotlin.model.bean

/**
 * Describe: java文件说明
 * Created by Yang on 2019/7/10  16:20
 */
data class HotKeyModel(
        val icon: String,
        val id: Int,
        val link: String,
        val name: String,
        val order: Int,
        val visible: Int
)