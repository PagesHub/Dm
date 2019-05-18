package com.yang.wandroid.ui

import androidx.recyclerview.widget.LinearLayoutManager
import com.yang.sdk.mvp.BaseFragment
import com.yang.sdk.utils.DisplayUtils
import com.yang.wandroid.R
import com.yang.wandroid.base.WABaseFragment
import com.yang.wandroid.weight.SpaceItemDecoration
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * @author        Yang
 * Description    HomeFragment界面
 * CreateDate     2019/5/17 10:55
 */
class HomeFragment : WABaseFragment() {
    override fun bindLayout(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {
        homeRv.run {
            layoutManager = LinearLayoutManager(mContext)
            addItemDecoration(SpaceItemDecoration(DisplayUtils.dp2px(mContext,2f)))
        }
    }

}