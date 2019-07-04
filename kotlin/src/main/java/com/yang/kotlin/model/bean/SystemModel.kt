package com.yang.kotlin.model.bean

/**
 * Describe: java文件说明
 * Created by Yang on 2019/7/4  16:58
 */

data class SystemModel(
        val children: List<SystemChildModel>,
        val courseId: Int,
        val id: Int,
        val name: String,
        val order: Int,
        val parentChapterId: Int,
        val userControlSetTop: Boolean,
        val visible: Int
)