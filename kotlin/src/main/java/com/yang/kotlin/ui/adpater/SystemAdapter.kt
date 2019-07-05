package com.yang.kotlin.ui.adpater

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yang.kotlin.R
import com.yang.kotlin.model.bean.SystemModel

/**
 * Describe: java文件说明
 * Created by Yang on 2019/7/4  18:12
 */
class SystemAdapter(layoutResId: Int = R.layout.fragment_kt_system_item) : BaseQuickAdapter<SystemModel, BaseViewHolder>(layoutResId) {
    override fun convert(helper: BaseViewHolder, item: SystemModel) {
        helper.setText(R.id.systemParent, item.name)
                .setText(R.id.systemChild, item.children.joinToString("      ", transform = { systemChildModel ->
                    systemChildModel.name
                }))
    }
}