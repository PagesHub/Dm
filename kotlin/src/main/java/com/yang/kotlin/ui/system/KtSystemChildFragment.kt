package com.yang.kotlin.ui.system

import com.yang.kotlin.R
import com.yang.kotlin.base.KotlinFragment
import com.yang.kotlin.model.bean.SystemChildModel
import com.yang.sdk.utils.LogUtils
import com.zhangyue.we.x2c.ano.Xml
import kotlinx.android.synthetic.main.fragment_kt_system_child.*

/**
 * Describe: java文件说明
 * Created by Yang on 2019/7/5  11:49
 */
@Xml(layouts = ["fragment_kt_system_child"])
class KtSystemChildFragment(systemChildModel: SystemChildModel) : KotlinFragment<KtSystemChildViewModule>() {
    private var mSystemChildModel = systemChildModel

    override fun providerVMClass(): Class<KtSystemChildViewModule>? = KtSystemChildViewModule::class.java

    override fun bindLayout(): Int {
        return R.layout.fragment_kt_system_child
    }

    override fun initView() {

        LogUtils.eLog(mSystemChildModel.toString())
         txvName.text=mSystemChildModel.name
    }
}