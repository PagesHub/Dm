package com.yang.kotlin.ui.adpater

import android.text.Html
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yang.kotlin.R
import com.yang.kotlin.model.bean.ProjectTypeModel

/**
 * Describe: java文件说明
 * Created by Yang on 2019/7/8  16:42
 */
class ProjectTypeAdapter(layoutResId: Int = R.layout.fragment_kt_project_type) : BaseQuickAdapter<ProjectTypeModel, BaseViewHolder>(layoutResId) {
    private var mPosition = 0

    override fun convert(helper: BaseViewHolder, item: ProjectTypeModel) {
        helper.setText(R.id.txv_tab, Html.fromHtml(item.name))
        helper.getView<TextView>(R.id.txv_tab).isSelected = mPosition == helper.adapterPosition
    }

    fun setPosition(position: Int) {
        this.mPosition = position
        notifyDataSetChanged()
    }
}