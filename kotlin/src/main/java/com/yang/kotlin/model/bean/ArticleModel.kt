package com.yang.kotlin.model.bean

/**
 * Describe: java文件说明
 * Created by Yang on 2019/7/3  15:47
 */
data class Article(
        val apkLink: String,
        val author: String,
        val chapterId: Int,
        val chapterName: String,
        val collect: Boolean,
        val courseId: Int,
        val desc: String,
        val envelopePic: String,
        val fresh: Boolean,
        val id: Int,
        val link: String,
        val niceDate: String,
        val origin: String,
        val prefix: String,
        val projectLink: String,
        val publishTime: Long,
        val superChapterId: Int,
        val superChapterName: String,
        val tags: List<Tag>,
        val title: String,
        val type: Int,
        val userId: Int,
        val visible: Int,
        val zan: Int
)

data class Tag(
    val name: String,
    val url: String
)