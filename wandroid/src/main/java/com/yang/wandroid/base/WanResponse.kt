package com.yang.wandroid.base

/**
 * @author        Yang
 * Description    java类作用描述
 * CreateDate     2019/5/17 10:55
 */
data class WanResponse<out T>(val errorCode: Int, val errorMsg: String, val data: T)
