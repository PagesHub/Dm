package com.yang.sdk.web;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.yang.sdk.R;
import com.yang.sdk.constant.Constants;
import com.yang.sdk.mvp.BaseActivity;

public class WebActivity extends BaseActivity {
    private X5WebView mWebView;
    private String mUrl, mTitle;

    @Override
    protected void getBundleExtras(Bundle extras) {
        mUrl = extras.getString(Constants.WEB_URL);
        mTitle = extras.getString(Constants.WEB_TITLE);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_web;
    }

    @Override
    protected void initView() {
        setToolbarTitle(Html.fromHtml(mTitle).toString(), R.menu.web_menu, true);
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

    @Override
    protected void onDestroy() {
        if( mWebView!=null) {
            // 如果先调用destroy()方法，则会命中if (isDestroyed()) return;    这一行代码，需要先onDetachedFromWindow()，再destory()
            ViewParent parent = mWebView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(mWebView);
            }
            mWebView.stopLoading();
            // 退出时调用此方法，移除绑定的服务，否则某些特定系统会报错
            mWebView.getSettings().setJavaScriptEnabled(false);
            mWebView.clearHistory();
            mWebView.clearView();
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView=null;
        }
        super.onDestroy();
    }
}
