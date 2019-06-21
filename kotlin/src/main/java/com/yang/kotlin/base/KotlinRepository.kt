package com.yang.kotlin.base

/**
 * Describe: kotlin 基础数据请求
 * Created by Yang on 2019/6/21  10:02
 */
open class KotlinRepository {

    suspend fun <T : Any> apiCall(call: suspend () -> KotlinResponse<T>): KotlinResponse<T> {
        return call.invoke()
    }
}