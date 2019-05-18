package com.yang.wandroid.weight

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


/**
 * @author        Yang
 * Description    RecyclerView条目间距
 * CreateDate     2019/5/17 10:55
 */
class SpaceItemDecoration(space: Int) : RecyclerView.ItemDecoration() {

    var mSpace = space

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = 0
        outRect.bottom = mSpace
    }
}