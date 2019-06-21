package com.yang.kotlin.base

/**
 * Describe: 玩安卓 kotlin基础Response
 * Created by Yang on 2019/6/21  9:38
 */
data class KotlinResponse<out T>(val errorCode: Int, val errorMsg: String, val data: T)