package com.yang.sdk.web;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.yang.sdk.R;
import com.yang.sdk.utils.DisplayUtils;

import java.util.Map;

import androidx.core.content.ContextCompat;

/**
 * Describe:  基于Tencent的x5 webView
 * Created by Yang on 2019/4/15.
 */
public class X5WebView extends WebView {
    private Context mContext;
    /**
     * 是否显示进度条,默认不显示
     */
    private boolean isShowProgress = true;
    /**
     * 顶部进度条
     */
    private WebProgressBar mProgressBar;

    public X5WebView(Context context) {
        this(context, null);
    }

    public X5WebView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public X5WebView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, false);
    }

    public X5WebView(Context context, AttributeSet attributeSet, int i, boolean b) {
        this(context, attributeSet, i, null, b);
    }

    public X5WebView(Context context, AttributeSet attributeSet, int i, Map<String, Object> map, boolean b) {
        super(context, attributeSet, i, map, b);
        this.mContext = context;
        basicSettings();
        initWebViewSettings();
    }

    /**
     * 部分设置
     */
    private void basicSettings() {
        //初始化进度条
        if (isShowProgress)
            initProgressBar();
        this.setWebViewClient(mWebClient);
        this.clearCache(true);
        this.getView().setClickable(true);
        this.setVerticalScrollBarEnabled(false);
        this.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
    }

    /**
     * 设置进度条
     */
    private void initProgressBar() {
        mProgressBar = new WebProgressBar(mContext);
        mProgressBar.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtils.dp2px(mContext, 1)));
        mProgressBar.setProgress(5);
        //把进度条加到WebView中
        addView(mProgressBar);
        this.setWebChromeClient(mWebChromeClient);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebViewSettings() {
        WebSettings webSetting = this.getSettings();
        //是否支持 Js 使用
        webSetting.setJavaScriptEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        //是否允许在WebView中访问内容URL，默认允许
        webSetting.setAllowContentAccess(true);
        // 开启DOM缓存
        webSetting.setDomStorageEnabled(true);
        // 开启数据库缓存
        webSetting.setDatabaseEnabled(true);
        // 支持自动加载图片
        webSetting.setLoadsImagesAutomatically(true);
        //设置 WebView 的缓存模式
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);
        //支持跨域访问
        //No 'Access-Control-Allow-Origin' header is present on the requested resource.
        webSetting.setAllowUniversalAccessFromFileURLs(true);
        // 支持启用缓存模式
        webSetting.setAppCacheEnabled(true);
        // 关闭密码保存提醒功能
        webSetting.setSavePassword(false);
        //可任意比例缩放
        webSetting.setUseWideViewPort(true);
        //允许WebView访问文件数据
        webSetting.setAllowFileAccess(true);
        //支持通过JS打开新窗口
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        //WebView是否需要用户的手势进行媒体播放，默认值为true
        webSetting.setMediaPlaybackRequiresUserGesture(false);
        //设置布局方式
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        //设置是否支持变焦
        webSetting.setSupportZoom(false);
        //设置是否支持缩放
        webSetting.setBuiltInZoomControls(false);
        //是否允许WebView度超出以概览的方式载入页面，默认false
        webSetting.setLoadWithOverviewMode(true);
        //DOM存储API是否可用，默认false
        webSetting.setDomStorageEnabled(true);
        //定位是否可用，默认为true
        //webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
    }

    /**
     * WebChromeClient
     */
    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView webView, int newProgress) {
            if (newProgress == 100) {//加载完毕进度条消失
                mProgressBar.setVisibility(View.GONE);
            } else {//更新进度
                mProgressBar.setVisibility(View.VISIBLE);
                mProgressBar.setProgress(newProgress);
            }
            super.onProgressChanged(webView, newProgress);
        }
    };
    /**
     * WebViewClient
     */
    private WebViewClient mWebClient = new WebViewClient() {
        /**
         * 防止加载网页时调起系统浏览器
         */
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    };

    /**
     * 是否显示进度条
     *
     * @param showProgress
     */
    public void setShowProgress(boolean showProgress) {
        isShowProgress = showProgress;
    }
}
