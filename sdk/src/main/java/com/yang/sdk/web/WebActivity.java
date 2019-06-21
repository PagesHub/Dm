package com.yang.sdk.web;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;

import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.yang.sdk.R;
import com.yang.sdk.constant.Constant;
import com.yang.sdk.mvp.BaseActivity;
import com.zhangyue.we.x2c.ano.Xml;

@Xml(layouts = "activity_web")
public class WebActivity extends BaseActivity {
    private X5WebView mWebView;
    private String mUrl, mTitle;

    @Override
    protected void getBundleExtras(Bundle extras) {
        mUrl = extras.getString(Constant.WEB_URL);
        mTitle = extras.getString(Constant.WEB_TITLE);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_web;
    }

    @Override
    protected void initView() {
        setToolbarTitle(mTitle, R.menu.web_menu, true);
        mWebView = getView(R.id.webView);
        if (mUrl != null && !mUrl.isEmpty()) {
            if (mUrl.startsWith("www"))
                mUrl = "http://" + mUrl;
            mWebView.loadUrl(mUrl);
        }
        mWebView.setShowProgress(true);
        mToolbar.setOnMenuItemClickListener(item -> {
            int i = item.getItemId();
            if (i == R.id.action_browser) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(mUrl)));
            } else if (i == R.id.action_share) {
                doShare();
            }
            return false;
        });
    }

    /**
     * 分享
     */
    private void doShare() {

    }
}
