package com.yang.kotlin.ui.adpater

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yang.kotlin.R
import com.yang.kotlin.model.bean.Article

/**
 * Describe: 文章列表适配器
 * Created by Yang on 2019/7/3  15:44
 */
class HomeArticleAdapter(layoutResId: Int = R.layout.fragment_kt_home_item) : BaseQuickAdapter<Article, BaseViewHolder>(layoutResId) {

    override fun convert(helper: BaseViewHolder, item: Article) {

        helper.setText(R.id.txv_title, item.title)
                .setText(R.id.txv_author, item.author)
    }

}
