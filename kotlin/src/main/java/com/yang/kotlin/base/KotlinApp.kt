package com.yang.kotlin.base

import com.yang.sdk.app.BaseApplication
import kotlin.properties.Delegates

/**
 * Describe: kotlin 中 Application
 * Created by Yang on 2019/6/21  9:25
 */
class KotlinApp : BaseApplication() {
    companion object {
        var INSTANCE: KotlinApp by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

}