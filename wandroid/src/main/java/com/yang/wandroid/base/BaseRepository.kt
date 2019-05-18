package com.yang.wandroid.base

/**
 * @author        Yang
 * Description    java类作用描述
 * CreateDate     2019/5/17 10:55
 */
open class BaseRepository {

    suspend fun <T : Any> apiCall(call: suspend () -> WanResponse<T>): WanResponse<T> {
        return call.invoke()
    }
}