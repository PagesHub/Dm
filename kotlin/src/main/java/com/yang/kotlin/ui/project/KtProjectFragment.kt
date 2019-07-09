package com.yang.kotlin.ui.project

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.yang.kotlin.R
import com.yang.kotlin.base.KotlinFragment
import com.yang.kotlin.model.bean.BaseListModel
import com.yang.kotlin.model.bean.ProjectModel
import com.yang.kotlin.model.bean.ProjectTypeModel
import com.yang.kotlin.ui.adpater.ProjectAdapter
import com.yang.kotlin.ui.adpater.ProjectTypeAdapter
import com.yang.kotlin.weight.SpaceItemDecoration
import com.yang.sdk.constant.Constants
import com.yang.sdk.utils.DisplayUtils
import com.yang.sdk.web.WebActivity
import com.yang.sdk.weight.RecycleViewDivider
import com.zhangyue.we.x2c.ano.Xml
import kotlinx.android.synthetic.main.fragment_kt_project.*
import kotlinx.android.synthetic.main.fragment_kt_system_child.*

/**
 * Describe: java文件说明
 * Created by Yang on 2019/6/21  17:53
 */
@Xml(layouts = ["fragment_project"])
class KtProjectFragment : KotlinFragment<KtProjectViewModule>() {
    override fun providerVMClass(): Class<KtProjectViewModule>? = KtProjectViewModule::class.java

    private val mTypeAdapter: ProjectTypeAdapter by lazy { ProjectTypeAdapter() }
    private val mAdapter: ProjectAdapter by lazy { ProjectAdapter() }
    private var mPage = 0
    private var mCid = 0


    override fun bindLayout(): Int {
        return R.layout.fragment_kt_project
    }

    override fun initView() {
        initTypeRcy()
        initProjectRcy()

        projectSrl.run {
            setOnRefreshListener { refresh() }
            isRefreshing = true
        }
        mViewModel.getProjectType()
    }

    /**
     * 初始化项目分类列表
     */
    private fun initTypeRcy() {
        projectTypeRcy.run {
            layoutManager = LinearLayoutManager(mContext)
            adapter = mTypeAdapter
        }
        mTypeAdapter.run {
            setOnItemClickListener { _, _, position ->
                mCid = mTypeAdapter.getItem(position)!!.id
                mTypeAdapter.setPosition(position)
                refresh()
            }
        }
    }

    /**
     * 初始化项目分类列表
     */
    private fun initProjectRcy() {
        projectRcy.run {
            layoutManager = LinearLayoutManager(mContext)
            addItemDecoration(RecycleViewDivider(mContext, LinearLayoutManager.VERTICAL))
            adapter = mAdapter

        }
        mAdapter.run {
            setOnItemClickListener { _, _, position ->
                goToWeb(mAdapter.data[position].link, mAdapter.data[position].title)
            }
            setOnLoadMoreListener({ mViewModel.getProjectList(mPage, mCid) }, projectRcy)
        }
    }

    override fun startObserve() {
        mViewModel.apply {
            mProjectTypeModel.observe(this@KtProjectFragment, Observer {
                it?.let {
                    initTypeList(it)
                }
            })
            mProjectModelList.observe(this@KtProjectFragment, Observer {
                it?.let {
                    initProjectList(it)
                }
            })
        }
    }

    private fun refresh() {
        mAdapter.setEnableLoadMore(false)
        projectSrl.isRefreshing = true
        mPage = 0
        mViewModel.getProjectList(mPage, mCid)

    }

    /**
     * 跳转到WebActivity
     */
    private fun goToWeb(url: String, title: String) {
        val bundle = Bundle()
        bundle.putString(Constants.WEB_URL, url)
        bundle.putString(Constants.WEB_TITLE, title)
        readyGo(WebActivity::class.java, bundle)
    }

    /**
     * 设置化项目分类列表
     */
    private fun initTypeList(it: List<ProjectTypeModel>) {
        mTypeAdapter.run {
            setNewData(it)
            mCid = it[0].id
            mViewModel.getProjectList(mPage, mCid)
        }
    }

    /**
     * 初始化项目列表数据
     */
    private fun initProjectList(it: BaseListModel<ProjectModel>) {
        mAdapter.run {
            if (it.curPage > it.pageCount) {
                loadMoreEnd(true)
                return
            }
            if (projectSrl.isRefreshing) replaceData(it.datas)
            else addData(it.datas)
            setEnableLoadMore(true)
            loadMoreComplete()
        }
        projectSrl.isRefreshing = false
        mPage++
    }
}
