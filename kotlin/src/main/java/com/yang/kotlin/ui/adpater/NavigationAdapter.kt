package com.yang.kotlin.ui.adpater

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yang.kotlin.model.bean.NavigationModel

import com.yang.kotlin.R
import com.yang.kotlin.model.bean.ArticleModel
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import com.zhy.view.flowlayout.TagFlowLayout


/**
 * Describe: java文件说明
 * Created by Yang on 2019/7/9  11:36
 */
class NavigationAdapter(layoutResId: Int = R.layout.fragment_kt_navigation_item) : BaseQuickAdapter<NavigationModel, BaseViewHolder>(layoutResId) {
    private lateinit var mOnTabClickListener: OnTabClickListener

    override fun convert(helper: BaseViewHolder, item: NavigationModel) {
        helper.setText(R.id.txv_title, item.name)
        helper.getView<TagFlowLayout>(R.id.tagLayout).run {
            adapter = object : TagAdapter<ArticleModel>(item.articles) {
                override fun getView(parent: FlowLayout, position: Int, t: ArticleModel): View {
                    val textView = LayoutInflater.from(mContext).inflate(R.layout.fragment_kt_navigation_tab, parent, false) as TextView
                    textView.text = t.title
                    return textView
                }

                override fun getCount(): Int {
                    return item.articles.size
                }
            }
            setOnTagClickListener { _, position, _ ->
                mOnTabClickListener.onTabClick(item.articles[position])
                true
            }
        }
    }

    fun setOnTabClickListener(onTabClickListener: OnTabClickListener) {
        this.mOnTabClickListener = onTabClickListener
    }

    interface OnTabClickListener {
        fun onTabClick(articleModel: ArticleModel)
    }
}