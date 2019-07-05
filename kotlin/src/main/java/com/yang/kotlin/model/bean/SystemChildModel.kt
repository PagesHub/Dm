package com.yang.kotlin.model.bean

import java.io.Serializable

/**
 * Describe: java文件说明
 * Created by Yang on 2019/7/4  17:01
 */
data class SystemChildModel(
        val children: List<Any>,
        val courseId: Int,
        val id: Int,
        val name: String,
        val order: Int,
        val parentChapterId: Int,
        val userControlSetTop: Boolean,
        val visible: Int
):Serializable