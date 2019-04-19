package com.yang.sdk.web;

import android.os.Bundle;

import com.yang.sdk.R;
import com.yang.sdk.mvp.BaseActivity;
import com.zhangyue.we.x2c.ano.Xml;

@Xml(layouts = "activity_web")
public class WebActivity extends BaseActivity {

    X5WebView mWebView;
    private String mUrl, mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_web;
    }

    @Override
    protected void initView() {

    }
}
