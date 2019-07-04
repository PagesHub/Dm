package com.yang.kotlin.model.api

import com.yang.sdk.http.retrofit.RetrofitWrapper

/**
 * Describe: java文件说明
 * Created by Yang on 2019/7/3  17:49
 */
object RetrofitClient {
    val service: WAService by lazy { RetrofitWrapper.getInstance(WAService.BASE_URL).create(WAService::class.java) }
}