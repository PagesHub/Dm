package com.yang.kotlin.ui.adpater

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yang.kotlin.R
import com.yang.kotlin.model.bean.ProjectTypeModel

/**
 * Describe: java文件说明
 * Created by Yang on 2019/7/8  16:42
 */
class ProjectTypeAdapter(layoutResId: Int = R.layout.fragment_kt_project_type) : BaseQuickAdapter<ProjectTypeModel, BaseViewHolder>(layoutResId) {
    override fun convert(helper: BaseViewHolder, item: ProjectTypeModel) {
        helper.setText(R.id.txv_tab, item.name)
    }
}