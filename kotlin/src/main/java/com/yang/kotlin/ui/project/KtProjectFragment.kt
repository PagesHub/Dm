package com.yang.kotlin.ui.project

import com.yang.kotlin.R
import com.yang.kotlin.base.KotlinFragment
import com.zhangyue.we.x2c.ano.Xml

/**
 * Describe: java文件说明
 * Created by Yang on 2019/6/21  17:53
 */
@Xml(layouts = ["fragment_project"])
class KtProjectFragment : KotlinFragment<KtProjectViewModule>() {
    override fun providerVMClass(): Class<KtProjectViewModule>? = KtProjectViewModule::class.java


    override fun bindLayout(): Int {
        return R.layout.fragment_kt_project
    }

    override fun initView() {
    }
}