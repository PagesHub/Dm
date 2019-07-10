package com.yang.kotlin.ui.navigation

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.yang.kotlin.R
import com.yang.kotlin.base.KotlinFragment
import com.yang.kotlin.model.bean.ArticleModel
import com.yang.kotlin.model.bean.NavigationModel
import com.yang.kotlin.ui.adpater.NavigationAdapter
import com.yang.sdk.constant.Constants
import com.yang.sdk.web.WebActivity
import com.zhangyue.we.x2c.ano.Xml
import kotlinx.android.synthetic.main.fragment_kt_navigation.*

/**
 * Describe: java文件说明
 * Created by Yang on 2019/6/21  17:51
 */
@Xml(layouts = ["fragment_kt_navigation"])
class KtNavigationFragment : KotlinFragment<KtNavigationViewModule>() {

    override fun providerVMClass(): Class<KtNavigationViewModule>? = KtNavigationViewModule::class.java

    private val mAdapter: NavigationAdapter by lazy { NavigationAdapter() }


    override fun bindLayout(): Int {
        return R.layout.fragment_kt_navigation
    }

    override fun initView() {
        initRcy()
        mViewModel.getNavigation()
    }

    private fun initRcy() {
        navigationRcy.run {
            layoutManager = LinearLayoutManager(mContext)
            adapter = mAdapter
        }
        mAdapter.run {
            setOnItemChildClickListener { _, _, _ ->
            }
            setOnTabClickListener(object : NavigationAdapter.OnTabClickListener {
                override fun onTabClick(articleModel: ArticleModel) {
                    goToWeb(articleModel.link, articleModel.title)
                }

            })
        }
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

    override fun startObserve() {
        mViewModel.apply {
            mNavigationModel.observe(this@KtNavigationFragment, Observer {
                it?.let {
                    setData(it)
                }
            })
        }
    }

    private fun setData(it: List<NavigationModel>) {
        mAdapter.setNewData(it)
    }

}