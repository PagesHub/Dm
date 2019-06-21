package com.yang.kotlin.ui

import androidx.fragment.app.Fragment
import com.yang.kotlin.R
import com.yang.kotlin.base.KotlinActivity
import com.yang.kotlin.ui.home.KtHomeFragment
import com.yang.kotlin.ui.navigation.KtNavigationFragment
import com.yang.kotlin.ui.project.KtProjectFragment
import com.yang.kotlin.ui.system.KtSystemFragment
import com.zhangyue.we.x2c.ano.Xml

/**
 * Describe: kotlin玩安卓主界面
 * Created by Yang on 2019/6/20  16:51
 */
@Xml(layouts = ["activity_kt_main"])
class KtMainActivity : KotlinActivity<KtMainViewModule>() {

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

}
