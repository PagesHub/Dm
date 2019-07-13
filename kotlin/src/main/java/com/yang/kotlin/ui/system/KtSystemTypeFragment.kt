package com.yang.kotlin.ui.system

import androidx.fragment.app.FragmentStatePagerAdapter
import com.yang.kotlin.R
import com.yang.kotlin.base.KotlinFragment
import com.yang.kotlin.model.bean.SystemModel
import com.yang.kotlin.utils.Constants
import com.yang.sdk.utils.rxUtils.RxBus
import kotlinx.android.synthetic.main.fragment_kt_system_type.*
import kotlinx.android.synthetic.main.fragment_kt_system_type.tabLayout
import kotlinx.android.synthetic.main.fragment_kt_system_type.viewPager


/**
 * Describe: java文件说明
 * Created by Yang on 2019/7/5  10:07
 */
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
                RxBus.getInstanceBus().post(Constants.RX_SYSTEM_TYPE_DETACH, null)
            }
        }
        viewPager.adapter = object : FragmentStatePagerAdapter(childFragmentManager,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            override fun getItem(position: Int) = KtSystemChildFragment(mSystemModel.children[position])
            override fun getCount() = mSystemModel.children.size
            override fun getPageTitle(position: Int) = mSystemModel.children[position].name
        }
        tabLayout.setupWithViewPager(viewPager)
    }

}