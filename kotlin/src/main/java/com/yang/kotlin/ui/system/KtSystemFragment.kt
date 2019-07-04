package com.yang.kotlin.ui.system

import androidx.lifecycle.Observer
import com.yang.kotlin.R
import com.yang.kotlin.base.KotlinFragment
import com.yang.kotlin.model.bean.SystemModel
import com.zhangyue.we.x2c.ano.Xml

/**
 * Describe: java文件说明
 * Created by Yang on 2019/6/21  17:55
 */
@Xml(layouts = ["fragment_system"])
class KtSystemFragment : KotlinFragment<KtSystemViewModule>() {
    override fun providerVMClass(): Class<KtSystemViewModule>? = KtSystemViewModule::class.java


    override fun bindLayout(): Int {
        return R.layout.fragment_kt_system
    }

    override fun initView() {
        mViewModel.getSystemTree()
    }

    override fun startObserve() {
        mViewModel.run {
            mSystemModelList.observe(this@KtSystemFragment, Observer { it ->
                it?.let {
                    setSystemTree(it)
                }
            })
        }
    }

    private fun setSystemTree(it: List<SystemModel>) {

    }
}

