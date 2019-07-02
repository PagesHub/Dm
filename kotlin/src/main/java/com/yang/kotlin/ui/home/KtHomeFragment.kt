package com.yang.kotlin.ui.home

import androidx.recyclerview.widget.LinearLayoutManager
import com.yang.kotlin.R
import com.yang.kotlin.base.KotlinFragment
import com.zhangyue.we.x2c.ano.Xml
import kotlinx.android.synthetic.main.fragment_kt_home.*

/**
 * Describe: java文件说明
 * Created by Yang on 2019/6/21  17:46
 */
@Xml(layouts = ["fragment_kt_home"])
class KtHomeFragment : KotlinFragment<KtHomeViewModule>() {

    override fun providerVMClass(): Class<KtHomeViewModule>? = KtHomeViewModule::class.java

    override fun bindLayout(): Int {
        return R.layout.fragment_kt_home
    }

    override fun initView() {
        homeRv.layoutManager = object : LinearLayoutManager(activity) {}
    }
}