package com.yang.kotlin.ui.home

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.yang.kotlin.R
import com.yang.kotlin.base.KotlinFragment
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
    private val mBanner by lazy { Banner(mContext) }
    private val mAdapter by lazy { HomeArticleAdapter() }

    override fun bindLayout(): Int {
        return R.layout.fragment_kt_home
    }

    override fun initView() {
        intRecyclerView()
        initBanner()
        mViewModel.getBanners()
    }

    /**
     * 初始化适配器
     */
    private fun intRecyclerView() {
        mAdapter.run {
            setOnItemClickListener { _, _, position ->
                goToWeb(mAdapter.data[position].link, mAdapter.data[position].title)
            }
            addHeaderView(mBanner)
            setOnLoadMoreListener({}, homeRv)
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
        mBanner.run {
            // layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtils.dp2px(mContext, 230f))
            setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
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
        }
    }

    private fun setBanner(bannerList: List<BannerModel>) {
//        for (banner in bannerList) {
//            bannerImages.add(banner.imagePath)
//            bannerTitles.add(banner.title)
//            bannerUrls.add(banner.url)
//        }
//        mBanner.setImages(bannerImages)
//                .setBannerTitles(bannerTitles)
//                .setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
//                .setDelayTime(3000)
//        banner.start()
    }
}


