package com.yang.kotlin.ui.navigation

import com.yang.kotlin.R
import com.yang.kotlin.base.KotlinFragment
import com.zhangyue.we.x2c.ano.Xml

/**
 * Describe: java文件说明
 * Created by Yang on 2019/6/21  17:51
 */
@Xml(layouts = ["fragment_kt_navigation"])
class KtNavigationFragment : KotlinFragment<KtNavigationViewModule>() {

    override fun providerVMClass(): Class<KtNavigationViewModule>? = KtNavigationViewModule::class.java

    override fun bindLayout(): Int {
        return R.layout.fragment_kt_navigation
    }

    override fun initView() {

    }
}