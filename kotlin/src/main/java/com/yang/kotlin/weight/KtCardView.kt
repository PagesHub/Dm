package com.yang.kotlin.weight

import android.content.Context
import android.util.AttributeSet
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.yang.kotlin.R

/**
 * Describe: Kotlin CardView
 * Created by Yang on 2019/7/4  17:56
 */
class KtCardView : CardView {
    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context!!, attrs, defStyleAttr) {
        basicSettings()
    }

    private fun basicSettings() {
        useCompatPadding = true
        setCardBackgroundColor(ContextCompat.getColor(context, R.color.white))
    }
}