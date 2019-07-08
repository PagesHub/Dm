package com.yang.kotlin.ui.system

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.yang.kotlin.R
import com.yang.kotlin.base.KotlinFragment
import com.yang.kotlin.model.bean.BaseListModel
import com.yang.kotlin.model.bean.ArticleModel
import com.yang.kotlin.model.bean.SystemChildModel
import com.yang.kotlin.ui.adpater.TypeArticleAdapter
import com.yang.kotlin.utils.Constants
import com.yang.sdk.web.WebActivity
import com.zhangyue.we.x2c.ano.Xml
import kotlinx.android.synthetic.main.fragment_kt_system_child.*

/**
 * Describe: java文件说明
 * Created by Yang on 2019/7/5  11:49
 */
@Xml(layouts = ["fragment_kt_system_child"])
class KtSystemChildFragment(systemChildModel: SystemChildModel) : KotlinFragment<KtSystemChildViewModule>() {
    private var mSystemChildModel = systemChildModel
    private val mAdapter by lazy { TypeArticleAdapter() }
    private var mPage = 0

    override fun providerVMClass(): Class<KtSystemChildViewModule>? = KtSystemChildViewModule::class.java

    override fun bindLayout(): Int {
        return R.layout.fragment_kt_system_child
    }

    override fun initView() {
        initRcy()
        systemChildSrl.run {
            setOnRefreshListener { refresh() }
            isRefreshing = true
        }
        mViewModel.getSystemChild(mPage, mSystemChildModel.id)
    }

    private fun initRcy() {
        mAdapter.run {
            setOnItemClickListener { _, _, position ->
                goToWeb(mAdapter.data[position].link, mAdapter.data[position].title)
            }
            setOnLoadMoreListener({ mViewModel.getSystemChild(mPage, mSystemChildModel.id) }, systemChildRcy)
            openLoadAnimation()
            setNotDoAnimationCount(Constants.PAGE_NUMBER)
            openLoadAnimation(BaseQuickAdapter.ALPHAIN)
        }

        systemChildRcy.run {
            layoutManager = LinearLayoutManager(mContext)
            adapter = mAdapter
        }
    }

    private fun refresh() {
        mAdapter.setEnableLoadMore(false)
        systemChildSrl.isRefreshing = true
        mPage = 0
        mViewModel.getSystemChild(mPage, mSystemChildModel.id)

    }

    /**
     * 跳转到WebActivity
     */
    private fun goToWeb(url: String, title: String) {
        val bundle = Bundle()
        bundle.putString(com.yang.sdk.constant.Constants.WEB_URL, url)
        bundle.putString(com.yang.sdk.constant.Constants.WEB_TITLE, title)
        readyGo(WebActivity::class.java, bundle)
    }

    override fun startObserve() {
        mViewModel.apply {
            mArticleListModel.observe(this@KtSystemChildFragment, Observer {
                it?.run { initList(it) }
            })
        }
    }

    /**
     * 初始化列表
     */
    private fun initList(articleListModel: BaseListModel<ArticleModel>) {
        mAdapter.run {
            if (articleListModel.curPage > articleListModel.pageCount) {
                loadMoreEnd(false)
                return
            }
            if (systemChildSrl.isRefreshing) replaceData(articleListModel.datas)
            else addData(articleListModel.datas)
            setEnableLoadMore(true)
            loadMoreComplete()
        }
        systemChildSrl.isRefreshing = false
        mPage++
    }

}