package com.yang.kotlin.ui.project

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
import com.zhangyue.we.x2c.ano.Xml
import kotlinx.android.synthetic.main.fragment_kt_project.*

/**
 * Describe: java文件说明
 * Created by Yang on 2019/6/21  17:53
 */
@Xml(layouts = ["fragment_project"])
class KtProjectFragment : KotlinFragment<KtProjectViewModule>() {
    override fun providerVMClass(): Class<KtProjectViewModule>? = KtProjectViewModule::class.java

    private val mTypeAdapter: ProjectTypeAdapter by lazy { ProjectTypeAdapter() }
    private val mAdapter: ProjectAdapter by lazy { ProjectAdapter() }
    private var mPage=0


    override fun bindLayout(): Int {
        return R.layout.fragment_kt_project
    }

    override fun initView() {
        initTypeRcy()
        initProjectRcy()
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
            setOnItemClickListener { adapter, view, position ->
            }
        }
    }

    /**
     * 初始化项目分类列表
     */
    private fun initProjectRcy() {
        projectRcy.run {
            layoutManager = LinearLayoutManager(mContext)
            adapter = mAdapter

        }
        mAdapter.run {
            setOnItemClickListener { adapter, view, position ->
            }
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

    /**
     * 设置化项目分类列表
     */
    private fun initTypeList(it: List<ProjectTypeModel>) {
        mTypeAdapter.run {
            setNewData(it)
            mViewModel.getProjectList(mPage,it[0].id)
        }
    }

    /**
     * 初始化项目列表数据
     */
    private fun initProjectList(it: BaseListModel<ProjectModel>) {
        mAdapter.run {
            setNewData(it.datas)
        }
    }
}
