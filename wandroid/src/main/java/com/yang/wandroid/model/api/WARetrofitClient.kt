package com.yang.wandroid.model.api

import com.yang.sdk.http.retrofit.RetrofitWrapper

/**
 * @author        Yang
 * Description    java类作用描述
 * CreateDate     2019/5/17 10:55
 */
object WARetrofitClient : RetrofitWrapper(WApiService.BASE_URL) {

    val service: WApiService by lazy { create(WApiService::class.java) }
}