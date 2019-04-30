package com.yang.wandroid

import android.widget.TextView
import com.yang.sdk.mvp.BaseActivity
import com.zhangyue.we.x2c.ano.Xml

/**
 * @author        Yang
 * Description    java类作用描述
 * CreateDate     2019/4/26 11:26
 */
@Xml(layouts = ["activity_wa_main"])
class WAMainActivity : BaseActivity() {

    override fun bindLayout(): Int {
        return R.layout.activity_wa_main
    }

    override fun initView() {
        val textView = findViewById<TextView>(R.id.txv_center)
        textView.setText(R.string.app_name)
    }
}
