package com.yang.kotlin.ui.adpater

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yang.kotlin.R
import com.yang.kotlin.model.bean.ProjectModel
import com.yang.sdk.loader.GlideUtils

/**
 * Describe: java文件说明
 * Created by Yang on 2019/7/8  16:42
 */
class ProjectAdapter(layoutResId: Int = R.layout.fragment_kt_project_item) : BaseQuickAdapter<ProjectModel, BaseViewHolder>(layoutResId) {

    override fun convert(helper: BaseViewHolder, item: ProjectModel) {
        helper.setText(R.id.txv_title, item.title)
                .setText(R.id.txv_desc, item.desc)
                .setText(R.id.txv_author, item.author)
                .setText(R.id.txv_time, item.niceDate)
        GlideUtils.getInstance().glideLoad(mContext, item.envelopePic, helper.getView(R.id.imv_pic), R.drawable.shape_empty_bg)
    }
}