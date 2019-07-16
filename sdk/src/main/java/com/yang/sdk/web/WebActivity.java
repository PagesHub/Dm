package com.yang.sdk.web;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.Toast;

import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.tencent.sonic.sdk.SonicCacheInterceptor;
import com.tencent.sonic.sdk.SonicConfig;
import com.tencent.sonic.sdk.SonicEngine;
import com.tencent.sonic.sdk.SonicSession;
import com.tencent.sonic.sdk.SonicSessionConfig;
import com.tencent.sonic.sdk.SonicSessionConnection;
import com.tencent.sonic.sdk.SonicSessionConnectionInterceptor;
import com.yang.sdk.R;
import com.yang.sdk.constant.Constants;
import com.yang.sdk.mvp.BaseActivity;
import com.yang.sdk.web.vasSonic.OfflinePkgSessionConnection;
import com.yang.sdk.web.vasSonic.SonicRuntimeImpl;
import com.yang.sdk.web.vasSonic.SonicSessionClientImpl;

import java.util.Objects;

public class WebActivity extends BaseActivity {
    private X5WebView mWebView;
    private SonicSession sonicSession;
    private SonicSessionClientImpl sonicSessionClient;
    private String mUrl="https://github.com/", mTitle;

    @Override
    protected void getBundleExtras(Bundle extras) {
        mUrl = extras.getString(Constants.WEB_URL);
        mTitle = extras.getString(Constants.WEB_TITLE);
    }

    @Override
    protected int bindLayout() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        if (!SonicEngine.isGetInstanceAllowed()) {
            SonicEngine.createInstance(new SonicRuntimeImpl(getApplication()), new SonicConfig.Builder().build());
        }

        SonicSessionConfig.Builder sessionConfigBuilder = new SonicSessionConfig.Builder();
        sessionConfigBuilder.setSupportLocalServer(true);
        sessionConfigBuilder.setCacheInterceptor(new SonicCacheInterceptor(null) {
            @Override
            public String getCacheData(SonicSession session) {
                return null; // offline pkg does not need cache
            }
        });
        sessionConfigBuilder.setConnectionInterceptor(new SonicSessionConnectionInterceptor() {
            @Override
            public SonicSessionConnection getConnection(SonicSession session, Intent intent) {
                return new OfflinePkgSessionConnection(WebActivity.this, session, intent);
            }
        });
        // create sonic session and run sonic flow
        if (mUrl != null && !mUrl.isEmpty()) {
            if (mUrl.startsWith("www"))
                mUrl = "http://" + mUrl;
        }
        sonicSession = SonicEngine.getInstance().createSession(Objects.requireNonNull(mUrl), sessionConfigBuilder.build());
        if (null != sonicSession) {
            sonicSession.bindClient(sonicSessionClient = new SonicSessionClientImpl());
        } else {
            // this only happen when a same sonic session is already running, u can comment following codes to feedback as a default mode.
            // throw new UnknownError("create session fail!");
            Toast.makeText(this, "create sonic session fail!", Toast.LENGTH_LONG).show();
        }
        return R.layout.activity_web;
    }

    @Override
    protected void initView() {
        setToolbarTitle(Html.fromHtml(mTitle).toString(), R.menu.web_menu, true);
        mWebView = getView(R.id.webView);

        if (sonicSessionClient != null) {
            sonicSessionClient.bindWebView(mWebView);
            sonicSessionClient.clientReady();
        } else { // default mode
            mWebView.loadUrl(mUrl);
        }
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView webView, String url) {
                super.onPageFinished(webView, url);
                if (sonicSession != null) {
                    sonicSession.getSessionClient().pageFinish(url);
                }
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView webView, String url) {
                if (sonicSession != null) {
                    return (WebResourceResponse) sonicSession.getSessionClient().requestResource(url);
                }
                return null;
            }
        });
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
        if (mWebView != null) {// 如果先调用destroy()方法，则会命中if (isDestroyed()) return;    这一行代码，需要先onDetachedFromWindow()，再destory()
            ViewParent parent = mWebView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(mWebView);
            }
            if (null != sonicSession) {
                sonicSession.destroy();
                sonicSession = null;
            }
            mWebView.stopLoading();
            // 退出时调用此方法，移除绑定的服务，否则某些特定系统会报错
            mWebView.getSettings().setJavaScriptEnabled(false);
            mWebView.clearHistory();
            mWebView.clearView();
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
    }
}
