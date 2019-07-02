package com.yang.chat;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.yang.sdk.arounter.ARouterConstant;
import com.yang.sdk.arounter.ARouterUtils;
import com.yang.sdk.mvp.BaseActivity;
import com.zhangyue.we.x2c.ano.Xml;

/**
 * @author        Yang
 * Description    java类作用描述
 * CreateDate     2019/4/27 10:19
 */

@Xml(layouts = "activity_chat_main")
@Route(path = ARouterConstant.ChatPath.ACTIVITY_CHAT_MAIN)
public class ChatMainActivity extends BaseActivity {

    @Override
    protected int bindLayout() {
        return R.layout.activity_chat_main;
    }

    @Override
    protected void initView() {
        ARouterUtils.injectActivity(this);
    }
}
