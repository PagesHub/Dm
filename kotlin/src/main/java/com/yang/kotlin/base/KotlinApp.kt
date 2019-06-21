package com.yang.kotlin.base

import com.yang.sdk.app.BaseApplication

/**
 * Describe: kotlin 中 Application
 * Created by Yang on 2019/6/21  9:25
 */
class KotlinApp : BaseApplication() {

    val INSTANCE = KotlinApp()

    override fun onCreate() {
        super.onCreate()
    }


}