package com.yang.kotlin.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import com.alibaba.android.arouter.facade.annotation.Route
import com.yang.kotlin.R
import com.yang.kotlin.base.KotlinActivity
import com.yang.kotlin.model.bean.SystemModel
import com.yang.kotlin.ui.home.KtHomeFragment
import com.yang.kotlin.ui.navigation.KtNavigationFragment
import com.yang.kotlin.ui.project.KtProjectFragment
import com.yang.kotlin.ui.system.KtSystemFragment
import com.yang.kotlin.ui.system.KtSystemTypeFragment
import com.yang.kotlin.utils.Constants
import com.yang.sdk.arounter.ARouterConstant
import com.yang.sdk.utils.rxUtils.RxBus
import com.yang.sdk.utils.rxUtils.RxBusMessage
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
    private lateinit var mSystemTypeFragment: KtSystemTypeFragment

    override fun bindLayout(): Int {
        return R.layout.activity_kt_main
    }

    override fun initView() {
        mTabRes = resources.getStringArray(R.array.tab_title)
        initFragments()
        initViewPager()
        initRxBus()
    }

    private fun initRxBus() {
        RxBus.getInstanceBus().doSubscribe(RxBusMessage::class.java) {
            if (it.code == Constants.RX_SYSTEM_TYPE_INIT) {
                initTypeFragment(it.`object` as SystemModel)
            }
        }
    }

    /**
     * 初始化Fragments
     */
    private fun initFragments() {
        mFragments.add(KtHomeFragment())
        mFragments.add(KtSystemFragment())
        mFragments.add(KtProjectFragment())
        mFragments.add(KtNavigationFragment())
    }

    /**
     * viewPager绑定Fragment
     */
    private fun initViewPager() {
        viewPager.offscreenPageLimit = mTabRes.size
        viewPager.adapter = object : FragmentStatePagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            override fun getItem(position: Int) = mFragments[position]
            override fun getCount() = mTabRes.size
            override fun getPageTitle(position: Int) = mTabRes[position]
        }
        tabLayout.setupWithViewPager(viewPager)
    }

    private fun initTypeFragment(systemModel: SystemModel) {
        mSystemTypeFragment = KtSystemTypeFragment(systemModel)
        supportFragmentManager.beginTransaction().run {
            add(android.R.id.content, mSystemTypeFragment)
            setCustomAnimations(R.anim.pager_enter_animation, R.anim.pager_exit_animation)
            commitAllowingStateLoss()
        }
    }

}
