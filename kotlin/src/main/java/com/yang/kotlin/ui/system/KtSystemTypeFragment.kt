package com.yang.kotlin.ui.system

import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.yang.kotlin.R
import com.yang.kotlin.base.KotlinFragment
import com.yang.kotlin.model.bean.SystemModel
import com.yang.sdk.utils.rxUtils.RxBus
import com.yang.sdk.weight.TabLayoutMediator
import com.zhangyue.we.x2c.ano.Xml
import kotlinx.android.synthetic.main.fragment_kt_system_type.*
import kotlinx.android.synthetic.main.fragment_kt_system_type.tabLayout

/**
 * Describe: java文件说明
 * Created by Yang on 2019/7/5  10:07
 */
@Xml(layouts = ["fragment_kt_system_child"])
class KtSystemTypeFragment(systemModel: SystemModel) : KotlinFragment<KtSystemTypeViewModule>() {

    override fun providerVMClass(): Class<KtSystemTypeViewModule>? = KtSystemTypeViewModule::class.java

    private val mSystemModel = systemModel

    override fun bindLayout(): Int {
        return R.layout.fragment_kt_system_type
    }

    override fun initView() {
        mToolbar.run {
            title = mSystemModel.name
            setNavigationOnClickListener {
                RxBus.getInstanceBus().post(1,2)
            }
        }
        viewPager2.adapter = object : FragmentStateAdapter(childFragmentManager, lifecycle) {
            override fun getItemCount() = mSystemModel.children.size
            override fun createFragment(position: Int) = KtSystemChildFragment(mSystemModel.children[position])
        }
        TabLayoutMediator(tabLayout, viewPager2) { tab: TabLayout.Tab, position: Int ->
            tab.text = mSystemModel.children[position].name
        }.attach()
    }
}