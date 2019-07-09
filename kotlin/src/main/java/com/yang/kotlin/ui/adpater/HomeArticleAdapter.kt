package com.yang.kotlin.ui.adpater

import android.text.Html
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yang.kotlin.R
import com.yang.kotlin.model.bean.ArticleModel

/**
 * Describe: 文章列表适配器
 * Created by Yang on 2019/7/3  15:44
 */
class HomeArticleAdapter(layoutResId: Int = R.layout.fragment_kt_home_item) : BaseQuickAdapter<ArticleModel, BaseViewHolder>(layoutResId) {

    override fun convert(helper: BaseViewHolder, item: ArticleModel) {
        helper.setText(R.id.txv_title, Html.fromHtml(item.title))
                .setText(R.id.txv_author, item.author)
                .setText(R.id.txv_chapter, "${item.superChapterName} ${item.chapterName}")
                .setText(R.id.txv_time, item.niceDate)
    }

}
