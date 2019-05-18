package com.yang.wandroid.ui.home

import android.os.Bundle
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.yang.sdk.loader.BannerImageLoader
import com.yang.sdk.utils.DisplayUtils
import com.yang.sdk.web.WebActivity
import com.yang.wandroid.R
import com.yang.wandroid.base.WABaseFragment
import com.yang.wandroid.model.bean.ArticleList
import com.yang.wandroid.model.bean.BannerKt
import com.yang.wandroid.ui.adapter.HomeRvAdapter
import com.yang.wandroid.weight.SpaceItemDecoration
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import kotlinx.android.synthetic.main.fragment_whome.*

/**
 * @author        Yang
 * Description    HomeFragment界面
 * CreateDate     2019/5/17 10:55
 */
class HomeFragment : WABaseFragment<HomeViewModel>() {

    private val mBanner by lazy { Banner(mContext) }
    private val mBannerImages = mutableListOf<String>()
    private val mBannerTitles = mutableListOf<String>()
    private val mBannerUrls = mutableListOf<String>()
    private val mArticleAdapter by lazy { HomeRvAdapter() }
    private var mPage = 1


    override fun providerVMClass(): Class<HomeViewModel>? = HomeViewModel::class.java

    override fun bindLayout(): Int {
        return R.layout.fragment_whome
    }

    override fun initView() {
        homeRv.run {
            layoutManager = LinearLayoutManager(mContext)
            addItemDecoration(SpaceItemDecoration(DisplayUtils.dp2px(mContext, 2f)))
        }
        initBanner()
        initAdapter()
        mViewModel.getBanners()
        mViewModel.getArticles(mPage)
    }

    private fun initBanner() {
        mBanner.run {
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtils.dp2px(mContext, 250f))
            setImageLoader(BannerImageLoader())
            setOnBannerListener { position ->
                val bundle = Bundle()
                bundle.putString("Url", mBannerUrls[position])
                readyGo(WebActivity::class.java, bundle)
            }
        }
    }

    private fun initAdapter() {
        mArticleAdapter.run {
            setOnItemClickListener { _, _, position ->
                val bundle = Bundle()
                bundle.putString("Urk", mArticleAdapter.data[position].link)
                bundle.putString("Title", mArticleAdapter.data[position].title)
                readyGo(WebActivity::class.java, bundle)
            }
            setHeaderView(mBanner)
            setOnLoadMoreListener({
                mViewModel
            }, homeRv)
        }
        homeRv.adapter = mArticleAdapter
    }

    override fun startObserve() {
        mViewModel.apply {
            mBanner.observe(this@HomeFragment, Observer { it ->
                it?.let {
                    setBanner(it)
                }
                mArticleList.observe(this@HomeFragment, Observer { it ->
                    it?.let {
                        setArticles(it)
                    }
                })
            })
        }
    }

    private fun setArticles(articleList: ArticleList) {
        mArticleAdapter.setNewData(articleList.datas)
    }

    private fun setBanner(bannerLists: List<BannerKt>) {
        for (bannerKt in bannerLists) {
            mBannerImages.add(bannerKt.imagePath)
            mBannerTitles.add(bannerKt.title)
            mBannerUrls.add(bannerKt.url)
        }
        mBanner.setImages(mBannerImages)
                .setBannerTitles(mBannerTitles)
                .setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
                .setDelayTime(3000)
        mBanner.startAutoPlay()
    }

}