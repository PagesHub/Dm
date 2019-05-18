package com.yang.wandroid.ui.project

import androidx.recyclerview.widget.LinearLayoutManager
import com.yang.wandroid.R
import com.yang.wandroid.base.WABaseFragment
import kotlinx.android.synthetic.main.fragment_wproject.*

/**
 * @author        Yang
 * Description    ProjectFragment完整工程界面
 * CreateDate     2019/5/17 10:55
 */
class ProjectFragment:WABaseFragment<ProjecViewModel>() {
    override fun bindLayout(): Int {
        return R.layout.fragment_wproject
    }

    override fun initView() {
        projectRv.run {
            layoutManager=LinearLayoutManager(mContext)

        }

    }

}