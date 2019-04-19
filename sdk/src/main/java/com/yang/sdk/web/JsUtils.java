package com.yang.sdk.web;

import android.content.Context;

import com.tencent.smtt.sdk.WebView;
import com.yang.sdk.app.AppManager;
import com.yang.sdk.mvp.BaseActivity;

/**
 * Describe:  public class JsUtils extends AppToJsInterface {
 * Created by Yang on 2019/4/15.
 */
public class JsUtils extends AppToJsInterface {

    public JsUtils(Context context, WebView webView) {
        super(context, webView);
    }

    /**
     * 关闭当前activity
     */
    public void closeWindow(String callbackId) {
        BaseActivity activity = (BaseActivity) AppManager.getAppManager().currentActivity();
        if (activity != null) {
            activity.finish();
        }
    }


    /**
     * 返回上一页，如果是最后一页则关闭当前页面
     * @param callbackId
     */
    public void goBack(String callbackId){
        if(mWebView != null && mContext instanceof BaseActivity){
            if(mWebView.canGoBack()){
                mWebView.goBack();
            }else{
                ((BaseActivity)mContext).finish();
            }
        }else{
            if (mContext instanceof BaseActivity){
                ((BaseActivity) mContext).finish();
            }
        }
    }
}
