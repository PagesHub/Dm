package com.yang.kotlin.ui.home

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.yang.kotlin.R
import com.yang.kotlin.base.KotlinFragment
import com.yang.kotlin.model.bean.BaseListModel
import com.yang.kotlin.model.bean.ArticleModel
import com.yang.kotlin.model.bean.BannerModel
import com.yang.kotlin.ui.adpater.HomeArticleAdapter
import com.yang.sdk.constant.Constants
import com.yang.sdk.loader.BannerImageLoader
import com.yang.sdk.web.WebActivity
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.zhangyue.we.x2c.ano.Xml
import kotlinx.android.synthetic.main.fragment_kt_home.*


/**
 * Describe: java文件说明
 * Created by Yang on 2019/6/21  17:46
 */
@Xml(layouts = ["fragment_kt_home"])
class KtHomeFragment : KotlinFragment<KtHomeViewModule>() {

    override fun providerVMClass(): Class<KtHomeViewModule>? = KtHomeViewModule::class.java

    private val mBannerData = mutableListOf<BannerModel>()
    private val mAdapter by lazy { HomeArticleAdapter() }
    private val mBanner by lazy { Banner(mContext) }
    private var mPage = 0

    override fun bindLayout(): Int {
        return R.layout.fragment_kt_home
    }

    override fun initView() {
        intRecyclerView()
        initBanner()
        homeSrl.run {
            setOnRefreshListener { refresh() }
        }

        mViewModel.getBanners()
        mViewModel.getArticleList(mPage)
    }

    /**
     * 初始化适配器
     */
    private fun intRecyclerView() {
        mAdapter.run {
            setOnItemClickListener { _, _, position ->
                goToWeb(mAdapter.data[position].link, mAdapter.data[position].title)
            }
            setOnLoadMoreListener({ mViewModel.getArticleList(mPage) }, homeRv)
            openLoadAnimation()
            setNotDoAnimationCount(Constants.PAGE_NUMBER * 2)
            openLoadAnimation(BaseQuickAdapter.ALPHAIN)
            addHeaderView(mBanner)
        }
        homeRv.run {
            layoutManager = LinearLayoutManager(mContext)
            adapter = mAdapter
        }
    }

    /**
     * 初始化首页Banner
     */
    private fun initBanner() {
        val wm: WindowManager = mContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()
        wm.defaultDisplay.getMetrics(dm)
        val height = dm.widthPixels * (9f / 16f)
        mBanner.run {
            layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height.toInt())
            setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
            setIndicatorGravity(BannerConfig.RIGHT)
            setDelayTime(3000)
            setImageLoader(BannerImageLoader())
            setOnBannerListener { position ->
                goToWeb(mBannerData[position].url, mBannerData[position].title)
            }
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
            mBanners.observe(this@KtHomeFragment, Observer { it ->
                it?.let { setBanner(it) }
            })
            mArticleListModel.observe(this@KtHomeFragment, Observer { it ->
                it?.let { setArticles(it) }
            })
        }
    }

    private fun refresh() {
        mAdapter.setEnableLoadMore(false)
        homeSrl.isRefreshing = true
        mPage = 0
        mViewModel.getArticleList(mPage)
    }

    /**
     * 设置列表数据
     */
    private fun setArticles(articleListModel: BaseListModel<ArticleModel>) {
        mAdapter.run {
            if (homeSrl.isRefreshing) replaceData(articleListModel.datas)
            else
                addData(articleListModel.datas)
            setEnableLoadMore(true)
            loadMoreComplete()
        }
        homeSrl.isRefreshing = false
        mPage++
    }

    /**
     * 设置Banner
     */
    private fun setBanner(bannerList: List<BannerModel>) {
        mBannerData.addAll(bannerList)
        val bannerImages = mutableListOf<String>()
        val bannerTitles = mutableListOf<String>()
        for (bannerModel in mBannerData) {
            bannerImages.add(bannerModel.imagePath)
            bannerTitles.add(bannerModel.title)
        }
        mBanner.setImages(bannerImages)
                .setBannerTitles(bannerTitles)
        mBanner.start()
    }

    override fun onStart() {
        super.onStart()
        mBanner.startAutoPlay()
    }

    override fun onStop() {
        super.onStop()
        mBanner.stopAutoPlay()
    }
}


