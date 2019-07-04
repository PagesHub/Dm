package com.yang.kotlin.weight

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


/**
 * Describe: RecyclerView 条目间距
 * Created by Yang on 2019/7/4  15:31
 */
class SpaceItemDecoration(space: Int) : RecyclerView.ItemDecoration() {
    private val mPace = space
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = 0
        outRect.bottom = mPace
    }
}