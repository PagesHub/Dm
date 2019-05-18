package com.yang.wandroid.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.yang.sdk.mvp.BaseActivity
import com.yang.wandroid.R
import com.yang.wandroid.ui.home.HomeFragment
import com.yang.wandroid.ui.navigation.NavigationFragment
import com.yang.wandroid.ui.project.ProjectFragment
import com.yang.wandroid.ui.system.SystemFragment
import com.yang.wandroid.weight.TabLayoutMediator
import com.zhangyue.we.x2c.ano.Xml
import kotlinx.android.synthetic.main.activity_wa_main.*

/**
 * @author        Yang
 * Description    java类作用描述
 * CreateDate     2019/4/26 11:26
 */
@Xml(layouts = ["activity_wa_main"])
class WAMainActivity : BaseActivity() {

    private lateinit var mTabRes: Array<String>
    private val mFragments = arrayListOf<Fragment>()
    private val homeFragment by lazy { HomeFragment() }
    private val systemFragment by lazy { SystemFragment() }
    private val navigationFragment by lazy { NavigationFragment() }
    private val projectFragment by lazy { ProjectFragment() }

    override fun bindLayout(): Int {
        return R.layout.activity_wa_main
    }

    override fun initView() {
        mTabRes = resources.getStringArray(R.array.tab_title)
        initFragments()
        initViewPager()
    }

    /**
     * 初始化Fragments
     */
    private fun initFragments() {
        mFragments.add(homeFragment)
        mFragments.add(systemFragment)
        mFragments.add(navigationFragment)
        mFragments.add(projectFragment)
    }

    /**
     *设置viewPager2
     */
    private fun initViewPager() {
        viewPager2.adapter = object : FragmentStateAdapter(this) {
            override fun getItem(position: Int): Fragment {
                return mFragments[position]
            }
            override fun getItemCount(): Int {
                return mTabRes.size
            }
        }
        TabLayoutMediator(tabLayout, viewPager2, TabLayoutMediator.OnConfigureTabCallback { tab, position ->
            tab.text = mTabRes[position]
        }).attach()
    }


}
