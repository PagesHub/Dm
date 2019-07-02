package com.yang.kotlin.ui

import androidx.viewpager2.adapter.FragmentStateAdapter

import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.yang.kotlin.R
import com.yang.kotlin.base.KotlinActivity
import com.yang.kotlin.ui.home.KtHomeFragment
import com.yang.kotlin.ui.navigation.KtNavigationFragment
import com.yang.kotlin.ui.project.KtProjectFragment
import com.yang.kotlin.ui.system.KtSystemFragment
import com.yang.sdk.arounter.ARouterConstant
import com.yang.sdk.weight.TabLayoutMediator
import com.zhangyue.we.x2c.ano.Xml
import kotlinx.android.synthetic.main.activity_kt_main.*

/**
 * Describe: kotlin玩安卓主界面
 * Created by Yang on 2019/6/20  16:51
 */
@Xml(layouts = ["activity_kt_main"])
@Route(path = ARouterConstant.KotlinPath.ACTIVITY_KOTLIN_MAIN)
class KtMainActivity : KotlinActivity<KtMainViewModule>() {


    override fun providerVMClass(): Class<KtMainViewModule>? = KtMainViewModule::class.java

    private lateinit var mTabRes: Array<String>
    private val mFragments = arrayListOf<Fragment>()
    private val mHomeFragment by lazy { KtHomeFragment() }
    private val mSystemFragment by lazy { KtSystemFragment() }
    private val mProjectFragment by lazy { KtProjectFragment() }
    private val mNavigationFragment by lazy { KtNavigationFragment() }

    override fun bindLayout(): Int {
        return R.layout.activity_kt_main
    }

    override fun initView() {
        mTabRes = resources.getStringArray(R.array.tab_title)
        initFragments()
        initViewPager2()
    }

    /**
     * 初始化Fragments
     */
    private fun initFragments() {
        mFragments.add(mHomeFragment)
        mFragments.add(mSystemFragment)
        mFragments.add(mProjectFragment)
        mFragments.add(mNavigationFragment)
    }

    /**
     * viewPager2绑定Fragment
      */
    private fun initViewPager2() {
        viewPager2.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return mTabRes.size
            }
            override fun createFragment(position: Int): Fragment {
                return mFragments[position]
            }
        }
        TabLayoutMediator(tabLayout, viewPager2, TabLayoutMediator.OnConfigureTabCallback { tab, position ->
            tab.text = mTabRes[position]
        }).attach()
    }

}
