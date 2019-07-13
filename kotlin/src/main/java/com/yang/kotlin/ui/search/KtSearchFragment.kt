package com.yang.kotlin.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.yang.kotlin.R
import com.yang.kotlin.base.KotlinFragment
import com.yang.kotlin.model.bean.ArticleModel
import com.yang.kotlin.model.bean.BaseListModel
import com.yang.kotlin.model.bean.HotKeyModel
import com.yang.kotlin.ui.adpater.SearchArticleAdapter
import com.yang.kotlin.utils.Constants
import com.yang.sdk.utils.rxUtils.RxBus
import com.yang.sdk.web.WebActivity
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import kotlinx.android.synthetic.main.fragment_kt_home.*
import kotlinx.android.synthetic.main.fragment_kt_search.*

/**
 * Describe: java文件说明
 * Created by Yang on 2019/7/10  10:41
 */
class KtSearchFragment : KotlinFragment<KtSearchViewModule>() {

    override fun providerVMClass(): Class<KtSearchViewModule>? = KtSearchViewModule::class.java
    private val mAdapter by lazy { SearchArticleAdapter() }
    private var mKey = ""
    private var mPage = 0
    override fun bindLayout(): Int {
        return R.layout.fragment_kt_search
    }

    override fun initView() {
        initTitle()
        initRcy()
        searchSrl.run {
            setOnRefreshListener { gotoSearch() }
        }
        mViewModel.getFriend()
        mViewModel.getHotKey()
    }

    /**
     * 设置标题
     */
    private fun initTitle() {
        mToolbar.run {
            setNavigationOnClickListener {
                val imm: InputMethodManager = mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm?.run {
                    hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
                }
                RxBus.getInstanceBus().post(Constants.RX_SEARCH_PAGE_DETACH, null)
            }
        }
        mSearchView.run {
            findViewById<SearchView.SearchAutoComplete>(R.id.search_src_text).run {
                setHintTextColor(resources.getColor(R.color.white))
                textSize = 14f
                setTextColor(resources.getColor(R.color.white))
            }
            isIconified = false
            onActionViewExpanded()
            setOnQueryTextListener(mOnQueryTextListener)
        }
    }

    private fun initRcy() {
        mAdapter.run {
            setOnItemClickListener { _, _, position ->
                goToWeb(mAdapter.data[position].link, mAdapter.data[position].title)
            }
            setOnLoadMoreListener({ mViewModel.query(mPage, mKey) }, homeRv)
            openLoadAnimation()
            setNotDoAnimationCount(Constants.PAGE_NUMBER)
            openLoadAnimation(BaseQuickAdapter.ALPHAIN)
        }
        searchRcy.run {
            layoutManager = LinearLayoutManager(mContext)
            adapter = mAdapter
        }
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

    private val mOnQueryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextChange(newText: String?) = false

        override fun onQueryTextSubmit(query: String?): Boolean {
            query?.let {
                mKey = query
                gotoSearch()
            }
            return true
        }
    }

    override fun startObserve() {
        mViewModel.apply {
            mSearchModel.observe(this@KtSearchFragment, Observer {
                it?.let {
                    initList(it)
                }
            })
            mHotKeyList.observe(this@KtSearchFragment, Observer {
                it?.let {
                    initHotTab(it)
                }
            })
            mFriendList.observe(this@KtSearchFragment, Observer {
                it?.let {
                    initFriendTab(it)
                }
            })
        }
    }

    private fun initHotTab(it: List<HotKeyModel>) {
        txvHot.visibility = View.VISIBLE
        tagLayoutHot.run {
            adapter = object : TagAdapter<HotKeyModel>(it) {
                override fun getView(parent: FlowLayout?, position: Int, t: HotKeyModel): View {
                    val textView = LayoutInflater.from(mContext).inflate(R.layout.fragment_kt_navigation_tab, parent, false) as TextView
                    textView.text = t.name
                    return textView
                }

                override fun getCount() = it.size
            }
            setOnTagClickListener { _, position, _ ->
                mKey = it[position].name
                gotoSearch()
                true
            }
        }
    }

    private fun initFriendTab(it: List<HotKeyModel>) {
        txvFriend.visibility = View.VISIBLE
        tagLayoutFriend.run {
            adapter = object : TagAdapter<HotKeyModel>(it) {
                override fun getView(parent: FlowLayout?, position: Int, t: HotKeyModel): View {
                    val textView = LayoutInflater.from(mContext).inflate(R.layout.fragment_kt_navigation_tab, parent, false) as TextView
                    textView.text = t.name
                    return textView
                }

                override fun getCount() = it.size
            }
            setOnTagClickListener { _, position, _ ->
                goToWeb(it[position].link, it[position].name)
                true
            }
        }
    }

    private fun initList(it: BaseListModel<ArticleModel>) {
        mAdapter.run {
            if (searchSrl.isRefreshing) replaceData(it.datas)
            else addData(it.datas)
            setEnableLoadMore(true)
            loadMoreComplete()
        }
        searchSrl.isRefreshing = false
        mPage++
    }

    /**
     * 开始搜索
     */
    private fun gotoSearch() {
        nsvTab.visibility = View.GONE
        searchRcy.visibility = View.VISIBLE
        mSearchView.clearFocus()
        mAdapter.setEnableLoadMore(false)
        searchSrl.isRefreshing = false
        mPage = 0
        mViewModel.query(mPage, mKey)
    }
}